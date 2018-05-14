$(document).foundation();
var app = angular.module('module1', ['angular-loading-bar', 'ui.router', 'angular-growl', 'angular.filter']);


app.controller('welcomeController', function ($window, $scope, $state) {
  var user = $window.sessionStorage.getItem('userName');
  if (user == '' || user == null)
    $window.location.href = './index,html';
  $scope.logOut = function () {
    $window.sessionStorage.removeItem('userName');
    $window.location.href = './index,html';
  }
  $scope.userName = user;
  $state.go('schedule');
});





app.controller('pilotDataController', function ($scope, $window, growl, aircraftService) {

  $scope.scheduleDetail = JSON.parse($window.sessionStorage.getItem('schedule'));
  console.log($scope.scheduleDetail.pilots);
  $scope.pilotData = $scope.scheduleDetail.pilots;

})
app.controller('aircraftDataController', function ($scope, $window, growl, aircraftService) {

  $scope.scheduleDetail = JSON.parse($window.sessionStorage.getItem('schedule'));
  $scope.aircraftLoading = true;
  aircraftService.getAircraftData($scope.scheduleDetail.aircraftCode).then(function (data) {
    $scope.aircraftLoading = false;
    if (data.data == null || data.data == '') {
      growl.error("ERROR in getting Aircraft Data! Do Again  ", {
        ttl: 4000
      });
    }
    else {
      console.log(data.data);
      $scope.aircraftData = data.data;


    }
  });

})
app.service('aircraftService', ['$http', '$window', 'growl', function ($http, $window, $state, growl) {

  this.getAircraftData = function (code) {

    return (

      $http.get('/RiskAssessment/aircraft/' + code).success(function (data, response) {


        return data;
      }).error(function (response, status) {

        return status;

      })
    );
  }
}]);



app.controller('environmentDataController', function ($scope, growl, envService) {

  $scope.analysisLoading = true;
  envService.getEnvData().then(function (data) {
    $scope.analysisLoading = false;
    if (data.data == null || data.data == '') {
      growl.error("ERROR in getting Environment Data! Do Again  ", {
        ttl: 4000
      });
    }
    else {
      console.log(data.data);
      $scope.environmentData = data.data;


    }
  });

})
app.service('envService', ['$http', '$window', function ($http, $window, $state) {

  this.getEnvData = function (code) {

    return (

      $http.get('/RiskAssessment/environment').success(function (data, response) {


        return data;
      }).error(function (response, status) {

        return status;

      })
    );
  }
}]);






app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {


  $urlRouterProvider.otherwise('/');

  $stateProvider

    .state('schedule', {
      url: '/schedule',
      templateUrl: 'schedule.html',
      controller: 'scheduleController',
    })
    .state('analysisHome', {
      url: '/analysisHome',
      templateUrl: 'analysisHome.html',
      controller: 'analysisHomeController',

    })
    .state('analysisHome.aircraft', {
      url: '/aircraft',
      templateUrl: 'aircraft.html',
      controller: 'aircraftController',

    })
    .state('analysisHome.aircraftData', {
      url: '/aircraftData',
      templateUrl: 'aircraftData.html',
      controller: 'aircraftDataController'
    })
    .state('analysisHome.destinationAirport', {
      url: '/destinationAirport',
      templateUrl: 'destinationAirport.html',
      controller: 'destinationAirportController'
    })
    .state('analysisHome.environmentData', {
      url: '/environmentData',
      templateUrl: 'environmentData.html',
      controller: 'environmentDataController'
    })
    .state('analysisHome.humanPerformance', {
      url: '/humanPerformance',
      templateUrl: 'humanPerformance.html',
      controller: 'humanPerformanceController'
    })
    .state('analysisHome.pilotData', {
      url: '/pilotData',
      templateUrl: 'pilotData.html',
      controller: 'pilotDataController'
    })
    .state('analysisHome.profeciency', {
      url: '/profeciency',
      templateUrl: 'profeciency.html',
      controller: 'profeciencyController'
    })
    .state('analysisHome.result', {
      url: '/result',
      templateUrl: 'result.html',
      controller: 'resultController'
    })
    .state('analysisHome.sourceAirport', {
      url: '/sourceAirport',
      templateUrl: 'sourceAirport.html',
      controller: 'sourceAirportController'
    })
}]);
app.config(['growlProvider', function (growlProvider) {
  growlProvider.globalDisableCountDown(true);


}]);

$(document).ready(function () {
  $('#example').DataTable();
});