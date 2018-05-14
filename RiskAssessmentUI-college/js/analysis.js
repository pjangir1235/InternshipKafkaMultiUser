var app = angular.module('module1');
app.controller('analysisHomeController', function ($rootScope, $window, $scope, $state, analysisService, growl) {
  $scope.analysisLoading = false;
  $scope.scheduleDetail = JSON.parse($window.sessionStorage.getItem('schedule'));

  $scope.firstPilot = $scope.scheduleDetail.firstPilot;
  $scope.aircraftCode = $scope.scheduleDetail.aircraftCode;
  $scope.captain = $scope.scheduleDetail.captain;
  $scope.sourceDate = $scope.scheduleDetail.dateOfDeparture;
  $scope.sourceLocation = $scope.scheduleDetail.sourceAirportCode;
  $scope.destinationLocation = $scope.scheduleDetail.destinationAirportCode;

  analysisService.doAnalysisData($scope.scheduleDetail).then(function (data) {
    $scope.analysisLoading = false;
    if (data.data == null || data.data == '') {

      growl.error("ERROR in Analysis! Do Again  ", {
        ttl: 4000
      });
    }
    else {
      console.log(data.data);
      $scope.result = data.data;
      $state.go('analysisHome.result');


    }
  });

})
app.service('analysisService', ['$http', 'growl', function ($http, $state, growl) {

  var result = {};
  this.doAnalysisData = function (data) {

    return (

      $http.post('/RiskAssessment/analysis', data).success(function (data, response) {
        result = data;
        console.log('result=' + data);
        return data;
      }).error(function (response, status) {
        console.log(status);
        return status;

      })
    );
  }
  this.getAnalysisData = function () {
    return result;
  }

}]);


app.controller('aircraftController', function ($scope, analysisService) {
  var aircraftData = analysisService.getAnalysisData().aircraftCheckList;
  var result = analysisService.getAnalysisData().result;
  $scope.stormScope = aircraftData.stormScope;
  $scope.weatherRadar = aircraftData.wetherRadar;
  $scope.deIce = aircraftData.deIce;
  $scope.autoPilot = aircraftData.autoPilot;
  $scope.perAircraft = result.aircraft;
  console.log(aircraftData);
})
app.controller('destinationAirportController', function ($scope, analysisService) {
  var destEnvData = analysisService.getAnalysisData().envDestination;
  var result = analysisService.getAnalysisData().result;
  $scope.dMountain = destEnvData.mountain;
  $scope.dNight = destEnvData.nightOperation;
  $scope.dWinter = destEnvData.winterOper;
  $scope.dThunderStorm = destEnvData.thunderStorm;
  $scope.dRain = destEnvData.rain;
  $scope.dForzen = destEnvData.frozen;
  $scope.dNoWeather = destEnvData.noWeather;
  $scope.dWind = destEnvData.windSpeed;
  $scope.dVisibility = destEnvData.visibility;
  $scope.perDest = result.destinationEnvironment;
  console.log(destEnvData);


})
app.controller('humanPerformanceController', function ($scope, analysisService) {
  var humanData = analysisService.getAnalysisData().crewTotal;
  var result = analysisService.getAnalysisData().result;
  $scope.dutyDay = humanData.dutyTime;
  $scope.restTime = humanData.restTime;
  $scope.perHuman = result.human;
  console.log(humanData);
})
app.controller('profeciencyController', function ($scope, $rootScope, analysisService) {
  var captainData = analysisService.getAnalysisData().captainDetail;
  var pilotData = analysisService.getAnalysisData().pilotDetail;
  var result = analysisService.getAnalysisData().result;
  $scope.cTotal = captainData.totHour;
  $rootScope.cTotalMsg = captainData.messTotHour;
  $scope.cDutyTime = captainData.durTot;
  $scope.cDutyTimeMsg = captainData.messDurTot;
  $scope.cNinty = captainData.durLNinty;
  $scope.cNintyMsg = captainData.messDurLNinty;
  $scope.pNoPilot = pilotData.noPilot;
  $scope.pNoPilotMsg = pilotData.messNoPilot;
  $scope.pNinty = pilotData.durLNinty;
  $scope.pNintyMsg = pilotData.messDurLNinty;
  $scope.pTotal = pilotData.totDur;
  $scope.pTotalMsg = pilotData.messTotDur;
  $scope.cLanding = captainData.landing;
  $scope.cLandingMsg = captainData.messLanding;
  $scope.perProficiency = result.proficiency;
  console.log(captainData);
  console.log(pilotData);


})
app.controller('sourceAirportController', function ($scope, analysisService) {
  var sourceEnvData = analysisService.getAnalysisData().envSource;
  var result = analysisService.getAnalysisData().result;
  $scope.sMountain = sourceEnvData.mountain;
  $scope.sNight = sourceEnvData.nightOperation;
  $scope.sWinter = sourceEnvData.winterOper;
  $scope.sThunderStorm = sourceEnvData.thunderStorm;
  $scope.sRain = sourceEnvData.rain;
  $scope.sForzen = sourceEnvData.frozen;
  $scope.sNoWeather = sourceEnvData.noWeather;
  $scope.sWind = sourceEnvData.windSpeed;
  $scope.sVisibility = sourceEnvData.visibility;
  $scope.perSource = result.sourceEnvironment;
  console.log(sourceEnvData);
})
app.controller('resultController', function ($scope, analysisService) {
  var resultData = analysisService.getAnalysisData().result;
  $scope.proficiency = resultData.proficiency;
  $scope.sourceEnv = resultData.sourceEnvironment;
  $scope.destEnv = resultData.destinationEnvironment;
  $scope.aircraft = resultData.aircraft;
  $scope.human = resultData.human;
  $scope.total = resultData.finalPercent;
  $scope.conclusion = resultData.finalConclusion;
  console.log(resultData);
})
