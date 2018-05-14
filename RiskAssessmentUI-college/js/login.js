


app.controller('loginController', function ($rootScope, $window, $scope, $state, loginVerification, growl) {
  $scope.loginLoading = false;
  $scope.login = function () {
    if ($scope.user == "" || $scope.user == null) {
      growl.warning("Enter User name", {
        ttl: 1500
      });
    }
    else if ($scope.password == "" || $scope.password == null) {
      growl.warning("Enter Password", {
        ttl: 1500
      });
    }
    else {
      var data = {
        'userName': $scope.user,
        'password': $scope.password
      }
      $scope.loginLoading = true;
      loginVerification.loginCheck(data).then(function (data) {
        $scope.loginLoading = false;
        console.log(data.data);
        if (data.data == false || data.data == null || data.data == '') {
          growl.error("Wrong ID and Password", {
            ttl: 1500
          });
        }
        else {
          $window.sessionStorage.setItem('userName', $scope.user);
          $window.location.href = "./home.html";
        }

      });

    }
  }

});
app.service('loginVerification', ['$http', '$window', 'growl', function ($http, $window, $state, growl) {
  this.loginCheck = function (data) {
    return (
      $http.post('/RiskAssessment/user', data).success(function (data, response) {
        console.log(response);
        console.log(data);
        return data;

      }).error(function (response, status) {

        console.log(status);
        return status;

      })
    );
  }

}]);