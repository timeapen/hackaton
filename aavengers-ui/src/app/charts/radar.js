class RadarController {
  /** @ngInject */
  constructor($http, $log) {
    $log.info('RADAR');

    $http
      .get('app/charts/radar.json')
      .then(response => {
        this.chartData = response.data;

        addIndices(this.chartData);
        addIndexRatings(this.chartData);

        $http
          .get('app/charts/radar-mock-series-data.json')
          .then(response => {
            $log.info("Series Data: ", response.data);
            const positionScores = response.data;

            //TODO: Colors on the radar chart should come from the server and be based on calculated risk factor
            $http
             .get('app/charts/radar-series.json')
             .then(response => {
               const scoresData = [];
               angular.forEach(positionScores, (score => {
                 const scoreChartData = {};
                 Object.assign(scoreChartData, response.data);

                 scoreChartData.text = score.id;
                 scoreChartData.values = score.values;

                 scoresData.push(scoreChartData);
               }));
               this.chartData.series = scoresData;
             });
          });
      });
  }

}

function addIndices(chartData) {
  const gaiaIndices = ["Quality", "Efficiency", "Satisfaction", "Value", "Cost"];
  chartData["scale-k"].values = gaiaIndices;
}

function addIndexRatings(chartData) {
  const gaiaIndexRatings = ["Poor", "Fair", "Good", "Very Good", "Excellent"];
  chartData["scale-v"].values = gaiaIndexRatings;
}

export const radar = {
  template: require('./radar.html'),
  controller: RadarController
};
