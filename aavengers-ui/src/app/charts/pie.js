class PieController {

  /** @ngInject */
  constructor($http, $log) {
    $log.info('PIE');

    $http
      .get('app/charts/pie.json')
      .then(response => {
        this.chartData = response.data;

        const colours = {};
        colours.RISK = '#FF0000';
        colours.NEAR = '#0000FF';
        colours.SAFE = '#00FF00';

        // const serverFirmId = '1234567890';
        // @TODO: obtain from API
        const serverAggregate = [{type: 'RISK', amount: 85948575}, {type: 'NEAR', amount: 119968796}, {type: 'SAFE', amount: 97503958}];

        const series = [];
        angular.forEach(serverAggregate, (value => {
          const type = value.type;
          const colour = colours[type];

          const seriesItem = {};
          seriesItem.values = [];
          seriesItem.values.push(value.amount);

          seriesItem.url = "./app/charts/pie/data/pie_level2_red.txt?id=1234567890&type=RISK";
          seriesItem.target = "graph";
          seriesItem.text = "@ RISK!";
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
      });
  }
}

export const pie = {
  template: require('./pie.html'),
  controller: PieController
};

