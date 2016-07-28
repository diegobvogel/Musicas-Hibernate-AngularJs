angular.module('musicas').controller('musicasController', function ($scope, $rootScope, $http) {
	
	$('#duracao').mask('00:00');
	$scope.acaoPagina = 'Nova música';
	$scope.isEdicao = false;
	$scope.musica = {
			nome : '',
			banda : '',
			duracao : ''
		};
	
	$scope.listarMusicas = function () {
		 $http.get("ws/musicas/list")
		    .then(function(response) {
		    	$scope.musicas = response.data;
		    }, function(response) {
		        alert('Houve algum problema ao carregar a lista de músicas.');
		    });
	}
	
	$scope.buscarMusica = function (codigo) {
		$scope.acaoPagina = 'Editar música';
		 $http.get("ws/musicas/getMusica/"+codigo)
		    .then(function(response) {
		    	$scope.musica = response.data;
		    	$scope.isEdicao = true;
		    }, function(response) {
		        alert('Não foi possível realizar a operação.');
		    });
	}
	
	$scope.salvar = function () {
		var url = "";
		if (!isFormularioValido($scope.musica)) {
			alert('Necessário preencher todos os campos');
			return;
		}
		if($scope.isEdicao){
			url = 'ws/musicas/editar';
		}else{
			url = 'ws/musicas/gravar';
		}
		$scope.params = {
			musica: $scope.musica
        };
		var config = {headers : {'Content-Type': 'application/json;charset=utf-8;'}};
		$http.post(url, $scope.params, config)
        .success(function (data, status, headers, config) {
        	if (data == false) {
        		alert('Não foi possível realizar a operação.');
        		return;
			}
        	$scope.acaoPagina = 'Nova música';
        	$scope.musica = {};
        	$scope.isEdicao = false;
        	$scope.listarMusicas();
        })
        .error(function (data, status, header, config) {
        	alert('Não foi possível realizar a operação.');
        });
	}
	
	
	$scope.deletar = function (codigo) {
		
		var url = 'ws/musicas/delete';
		$scope.params = {
        	codigo: codigo, 
        };
        var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        }
        
		$http.post(url, $scope.params, config)
        .success(function (data, status, headers, config) {
        	$scope.listarMusicas();
        })
        .error(function (data, status, header, config) {
        	alert('Não foi possível realizar a operação.');
        });
	}
	
	function isFormularioValido(obj){
		if (obj.nome == '' || obj.banda == '' || obj.duracao == '') {
			return false;
		}
		return true;
	}
	
});
