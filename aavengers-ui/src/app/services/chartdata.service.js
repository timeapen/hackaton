import angular from 'angular';

// Constants should be eventually pulled out into configuration data, retrieved from the server
const serverDomain = '//localhost:8080/gaia';
const colours = {Poor: '#F93F26', Fair: '#FCA311', Good: '#008500', VeryGood: '#6AADE4', Excellent: '#002888'};

class ChartData {

  /** @ngInject */
  constructor($http, $log) {
    this.$http = $http;
    this.$log = $log;
  }

  createRadarChartIndicators(accounts, indicators) {
    return this.getRadarChartFormat()
      .then(response => {
        const chartData = response.data;

        chartData["scale-k"].values = indicators.indicators;
        chartData["scale-v"].values = indicators.valueThresholds;

        return this.getRadarIndicatorScores(accounts)
          .then(response => {
            const riskScores = response.data.riskData;
            return this.getRadarSeriesDataFormat()
              .then(response => {
                chartData.series = this.createIndicatorScores(riskScores, indicators.indicators, response.data);
                return chartData;
              });
          });
      });
  }

  createIndicatorScores(riskScores, indicators, scoresFormat) {
    const scoresData = [];

    const scoreChartData = {};
    Object.assign(scoreChartData, scoresFormat);
    scoreChartData.text = 'Portfolio';

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
              .get(`${serverDomain}/indicators`);
  }

  getRadarIndicatorScores(accounts) {
    return this.$http
      .get(`${serverDomain}/portfolio/overallRisk/${accounts}`);
  }

  getRadarIndicatorTargets() {
    return this.$http
      .get(`${serverDomain}/indicators/settings`);
  }

  getRadarSeriesDataFormat() {
    return this.$http
     .get('app/charts/radar-series.json');
  }

  createPieChart(accounts, indicator) {
    return this.getPieChartFormat()
      .then(response => {
        const chartData = response.data;

        return this.getIndicatorRiskData(accounts, indicator)
          .then(response => {
            const serverAggregate = response.data;

            const series = [];
            angular.forEach(serverAggregate, (value => {
              series.push(this.createPieSeriesItem(value, accounts, indicator));
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

  getIndicatorRiskData(accounts, indicator) {
    return this.$http
            // series data from backend
            .get(`${serverDomain}/portfolio/indicatorRisk/${indicator}/${accounts}`);
  }

  createPieSeriesItem(position, accounts, indicator) {
    const type = position.type;
    const colour = colours[type];

    const seriesItem = {};
    seriesItem.values = [];
    seriesItem.values.push(position.amount);

    // URL for drill down pie chart
    seriesItem.url = `${serverDomain}/portfolio/detailedRisk/${indicator}/${type}/${accounts}`;

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

  getRadarChartTargetIndicatorData(targetIndicators) {
    return this.getRadarSeriesDataFormat()
      .then(response => {
        const scoresFormat = response.data;
        scoresFormat.lineColor = '#002888';

        const scoreChartData = {};
        Object.assign(scoreChartData, scoresFormat);
        scoreChartData.text = 'Target';

        const chartRiskScores = [];
        angular.forEach(targetIndicators, (targetIndicator => {
          chartRiskScores.push(targetIndicator.indicatorValue);
        }));
        scoreChartData.values = chartRiskScores;

        return scoreChartData;
      });
  }
}

export default angular.module('services.chartData', [])
  .service('chartData', ChartData)
  .name;
