import angular from 'angular';

class Indicators {

  /** @ngInject */
  constructor($http, $log) {
    $log.info("Creating Indicators Service");

    this.$http = $http;
    this.$log = $log;
    this.serverDomain = '//localhost:8080/gaia';
  }

  getPieChartIndicators() {
    this.$log.info("Generating pie chart indicators");

    return this.$http
      // obtain the Pie chart configuration from json on web server, series data comes from backend call
      .get('app/charts/pie/pie.json')
      .then(response => {
        const chartData = response.data;

        const colours = {};
        colours.RISK = '#FF0000';
        colours.NEAR = '#0000FF';
        colours.SAFE = '#00FF00';

        const id = '140735003';
        const indicator = 'CORRUPTION';

        return this.$http
          // series data from backend
          .get('app/charts/pie/pie-aggregate.json')
          .then(response => {
            const serverAggregate = response.data;

            const series = [];
            angular.forEach(serverAggregate, (value => {
              const type = value.type;
              const colour = colours[type];

              const seriesItem = {};
              seriesItem.values = [];
              seriesItem.values.push(value.amount);

              // URL for drill down pie chart
              seriesItem.url = `${this.serverDomain}/portfolio/detailedRisk/${id}/${type}/${indicator}`;
              this.$log.info('url: ', seriesItem.url);

              seriesItem.target = "graph";
              seriesItem.text = value.title;
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

              series.push(seriesItem);
            }));

            angular.merge(chartData.graphset[0], {series});
            return chartData;
          }
        );
      }
    );
  }
}

export default angular.module('services.indicators', [])
  .service('indicators', Indicators)
  .name;
