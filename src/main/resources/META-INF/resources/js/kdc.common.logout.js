!function () {
    "use strict";

    define(["jquery",
            "angular"],

        function ($, angular) {

            var module = angular.module("kdc.common.logout", []);

            module.controller("LogoutController", function ($scope, $http, $window) {

                $scope.logout = function () {
                    $http.post("/logout", {})
                        .success(function () {
                            $window.location.href = "/";
                        })
                        .error(function () {

                        });
                }
            });
        });
}();