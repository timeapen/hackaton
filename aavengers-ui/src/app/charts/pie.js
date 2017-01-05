class PieController {

  /** @ngInject */
  constructor($http, $log) {
    $log.info('PIE');

    $http
      .get('app/charts/pie/pie.json')
      .then(response => {
        this.chartData = response.data;

        const colours = {};
        colours.RISK = '#FF0000';
        colours.NEAR = '#0000FF';
        colours.SAFE = '#00FF00';

        const id = '140735003';
        const indicator = 'CORRUPTION';

        $http
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

              seriesItem.url = `//localhost:8080/gaia/portfolio/detailedRisk/${id}/${type}/${indicator}`;
              $log.info('url: ', seriesItem.url);

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

            angular.merge(this.chartData.graphset[0], {series});
          }
        );
      }
    );
  }
}

export const pie = {
  template: require('./pie.html'),
  controller: PieController
};

