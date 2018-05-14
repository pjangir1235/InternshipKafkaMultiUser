app.controller('scheduleController', function ($rootScope, $window, $scope, $state, scheduleService, growl) {
  $scope.scheduleLoading = true;
  $scope.table_hide = true;
  $scope.analysisButton = true;
  var dateAdd = (24 * 60 * 60 * 1000);

  scheduleService.getLocation().then(function (data) {
    $scope.scheduleLoading = false;
    $scope.location = data.data;
  });
  $scope.checkLocation = function () {
    $scope.dateValue = null;
  }
  $scope.getSchedule = function () {
    $scope.locations = [];
    $scope.table_hide = true;
    if ($scope.dateValue != null && $scope.dateValue != '' && $scope.locationValue != null && $scope.locationValue != '') {
      $scope.newdate = new Date(new Date($scope.dateValue.getTime() + dateAdd));
      $scope.newdate = $scope.newdate.toISOString().substr(0, 10);

      var data = {
        'location': $scope.locationValue,
        'date': $scope.newdate
      }
      $scope.scheduleLoading = true;
      scheduleService.getFlightSchedule(data).then(function (data) {
        $scope.scheduleLoading = false;
        if (data.data == null || data.data == '') {

          growl.error("No Flight scheduled in location " + $scope.locationValue + " on " + $scope.newdate, {
            ttl: 4000
          });
        }
        else {
          $scope.table_hide = false;
          $scope.analysisButton = false;
          $scope.schedules = data.data

        }

      });
    }
    $scope.analysis = function () {
      if ($scope.SelectedScheduleId == null || $scope.SelectedScheduleId == '')
        growl.warning("Select any one of Schedule ", {
          ttl: 2000
        });
      else {
        $window.sessionStorage.setItem('schedule', JSON.stringify($scope.schedules[$scope.SelectedScheduleId]));
        $state.go('analysisHome');
      }
    }
  }

})

app.service('scheduleService', ['$http', '$window', 'growl', function ($http, $window, $state, growl) {


  this.getLocation = function () {
    return (
      $http.get('/RiskAssessment/airport').success(function (data, response) {
        return data;
      }).error(function (response, status) {
        return status;

      })
    );

  }
  this.getFlightSchedule = function (data) {

    return (

      $http.post('/RiskAssessment/flightSchedule', data).success(function (data, response) {
        insertCaptains(data);
        return data;
      }).error(function (response, status) {
        console.log(status);
        return status;

      })
    );

  }
  function insertCaptains (schedules) {
    var i = 0;
    schedules.forEach(schedule => {
      var captain = schedule.pilots.find((pilot) => pilot.pilotDesignationCode == "PD1");
      if (captain) {
        schedule.captain = captain.pilotName;

      }
      else {
        schedule.captain = "NA";

      }
      var firstPilot = schedule.pilots.find((pilot) => pilot.pilotDesignationCode == "PD2");
      if (firstPilot) {
        schedule.firstPilot = firstPilot.pilotName;

      }
      else {
        schedule.firstPilot = "NA";

      }
      schedule.position = i++;
    });
  }
}]);