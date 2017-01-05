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

  createRadarChartIndicators() {
    this.$log.info("Generating radar chart indicators");

    return this.getRadarChartFormat()
          .then(response => {
            const chartData = response.data;

            return this.getGaiaIndicators()
              .then(response => {
                const indicators = response.data;
                chartData["scale-k"].values = indicators.indicators;
                chartData["scale-v"].values = indicators.valueThresholds;

                return this.getRadarIndicatorScores()
                                .then(response => {
                                  this.$log.info("Series Data: ", response.data);
                                  const positionScores = response.data;

                                  //  Colors on the radar chart should come from the server and be based on calculated risk factor
                                  return this.getRadarSeriesDataFormat()
                                   .then(response => {
                                     chartData.series = this.createIndicatorScores(positionScores, response.data);

                                     return chartData;
                                   });
                                });
              });
          });
  }

  createIndicatorScores(positionScores, scoresFormat) {
    const scoresData = [];
    angular.forEach(positionScores, (score => {
      const scoreChartData = {};
      Object.assign(scoreChartData, scoresFormat);

      scoreChartData.text = score.id;
      scoreChartData.values = score.values;

      scoresData.push(scoreChartData);
    }));
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

  getRadarIndicatorScores() {
    return this.$http
      .get('app/charts/radar-mock-indicator-scores.json');
  }

  getRadarSeriesDataFormat() {
    return this.$http
     .get('app/charts/radar-series.json');
  }

  addRadarIndices(chartData) {
    const gaiaIndices = ["Quality", "Efficiency", "Satisfaction", "Value", "Cost"];
    chartData["scale-k"].values = gaiaIndices;
  }

  addRadarIndexRatings(chartData) {
    const gaiaIndexRatings = ["Poor", "Fair", "Good", "Very Good", "Excellent"];
    chartData["scale-v"].values = gaiaIndexRatings;
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
    legendMarker.size = 7;
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
