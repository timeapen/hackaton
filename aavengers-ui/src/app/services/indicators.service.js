import angular from 'angular';

// Constants should be eventually pulled out into configuration data, retrieved from the server
const serverDomain = '//localhost:8080/gaia';
const colours = {RISK: '#FF0000', NEAR: '#0000FF', SAFE: '#00FF00'};

class Indicators {

  /** @ngInject */
  constructor($http, $log) {
    $log.info("Creating Indicators Service");
    this.$http = $http;
    this.$log = $log;
  }

  getPieChartIndicators(accountId, indicator) {
    this.$log.info("Generating pie chart indicators");

    return this.$http
      // obtain the Pie chart configuration from json on web server, series data comes from backend call
      .get('app/charts/pie/pie.json')
      .then(response => {
        const chartData = response.data;

        return this.$http
          // series data from backend
          .get('app/charts/pie/pie-aggregate.json')
          .then(response => {
            const serverAggregate = response.data;

            const series = [];
            angular.forEach(serverAggregate, (value => {
              series.push(createPieSeriesItem(value, accountId, indicator));
            }));

            angular.merge(chartData.graphset[0], {series});
            return chartData;
          }
        );
      }
    );
  }
}

function createPieSeriesItem(position, accountId, indicator) {
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

export default angular.module('services.indicators', [])
  .service('indicators', Indicators)
  .name;
