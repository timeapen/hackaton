import angular from 'angular';

// Constants should be eventually pulled out into configuration data, retrieved from the server
const serverDomain = '//localhost:8080/gaia';
const colours = {Poor: '#F93F26', Fair: '#FFD129', Good: '#008500', VeryGood: '#6AADE4', Excellent: '#002888'};

class Indicators {

  /** @ngInject */
  constructor($http, $log) {
    $log.info("Creating Indicators Service");
    this.$http = $http;
    this.$log = $log;
  }

  createRadarChartIndicators(companyId) {
    this.$log.info("Generating radar chart indicators for company id: ", companyId);

    return this.getRadarChartFormat()
          .then(response => {
            const chartData = response.data;

            return this.getGaiaIndicators()
              .then(response => {
                const gaiaMeasures = response.data;
                const indicators = gaiaMeasures.indicators;
                this.$log.info("Gaia indicators: ", indicators);
                chartData["scale-k"].values = indicators;
                chartData["scale-v"].values = gaiaMeasures.valueThresholds;

                return this.getRadarIndicatorScores(companyId)
                                .then(response => {
                                  this.$log.info("Series Data: ", response.data);
                                  const riskScores = response.data.riskData;

                                  //  Colors on the radar chart should come from the server and be based on calculated risk factor
                                  return this.getRadarSeriesDataFormat()
                                   .then(response => {
                                     chartData.series = this.createIndicatorScores(riskScores, indicators, response.data);

                                     return chartData;
                                   });
                                });
              });
          });
  }

  createIndicatorScores(riskScores, indicators, scoresFormat) {
    const scoresData = [];

    const scoreChartData = {};
    Object.assign(scoreChartData, scoresFormat);
    scoreChartData.text = 'Company Id';

    const chartRiskScores = [];
    angular.forEach(indicators, (indicator => {
      chartRiskScores.push(riskScores[indicator]);
    }));
    scoreChartData.values = chartRiskScores;

    scoresData.push(scoreChartData);
    return scoresData;
  }

  getRadarChartFormat() {
    return this.$http
              .get('app/charts/radar.json');
  }

  getGaiaIndicators() {
    return this.$http
              .get(`http://${serverDomain}/indicators`);
  }

  getRadarIndicatorScores(companyId) {
    return this.$http
      .get(`http://${serverDomain}/portfolio/overallRisk/${companyId}`);
  }

  getRadarSeriesDataFormat() {
    return this.$http
     .get('app/charts/radar-series.json');
  }

  createPieChart(accountId, indicator) {
    this.$log.info("Generating pie chart indicators");

    return this.getPieChartFormat()
      .then(response => {
        const chartData = response.data;

        return this.getIndicatorRiskData(accountId, indicator)
          .then(response => {
            const serverAggregate = response.data;

            const series = [];
            angular.forEach(serverAggregate, (value => {
              series.push(this.createPieSeriesItem(value, accountId, indicator));
            }));

            angular.merge(chartData.graphset[0].title, {text: indicator});
            angular.merge(chartData.graphset[0], {series});
            return chartData;
          }
        );
      }
    );
  }

  getPieChartFormat() {
    // obtain the Pie chart configuration from json on web server, series data comes from backend call
    return this.$http
              .get('app/charts/pie/pie.json');
  }

  getIndicatorRiskData(accountId, indicator) {
    return this.$http
            // series data from backend
            .get(`${serverDomain}/portfolio/indicatorRisk/${indicator}/${accountId}`);
  }

  createPieSeriesItem(position, accountId, indicator) {
    const type = position.type;
    const colour = colours[type];

    const seriesItem = {};
    seriesItem.values = [];
    seriesItem.values.push(position.amount);

    // URL for drill down pie chart
    seriesItem.url = `${serverDomain}/portfolio/detailedRisk/${accountId}/${type}/${indicator}`;

    seriesItem.target = "graph";
    seriesItem.text = position.title;
    seriesItem.backgroundColor = colour;
    seriesItem.legendText = "%t<br><b>$%v</b>";

    const legendMarker = {};
    legendMarker.type = "circle";
    legendMarker.size = 2;
    legendMarker.borderColor = colour;
    legendMarker.borderWidth = 4;
    legendMarker.backgroundColor = "#FFF";

    seriesItem.legendMarker = legendMarker;

    const legendItem = {};
    legendItem.backgroundColor = colour;

    seriesItem.legendItem = legendItem;

    return seriesItem;
  }
}
export default angular.module('services.indicators', [])
  .service('indicators', Indicators)
  .name;