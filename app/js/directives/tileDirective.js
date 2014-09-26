stp.directive('tileDirective', function($animate) {

  return {
        restrict: 'E',
        replace: true,
        templateUrl: 'app/partials/tile.tpl.html',
        controller: 'tileController',
        scope:{},
        link: function(scope, element) {
            console.log(element);
            scope.front = true;
            scope.back = false;
            element.on('click', function() {
              if ( scope.front ){
                scope.back = true;
                scope.front = false;
              } else {
                scope.back = false;
                scope.front = true;
              }
            });
          }
        };
});