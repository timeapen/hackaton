class PieController {

  /** @ngInject */
  constructor($http, $log) {
    $log.info('PIE');

    const serverDomain = '//localhost:8080/gaia';

    const id = '000133190USD|140735003';
    const indicator = 'Corruption';

    $http
      // obtain the Pie chart configuration from json on web server, series data comes from backend call
      .get('app/charts/pie/pie.json')
      .then(response => {
        this.chartData = response.data;

        const colours = {};
        colours.Poor = '#F93F26';
        colours.Fair = '#FFD129';
        colours.Good = '#008500';
        colours.VeryGood = '#6AADE4';
        colours.Excellent = '#002888';

        $http
          // series data from backend
          .get(`${serverDomain}/portfolio/indicatorRisk/${indicator}/${id}`)
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
              seriesItem.url = `${serverDomain}/portfolio/detailedRisk/${id}/${type}/${indicator}`;
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

