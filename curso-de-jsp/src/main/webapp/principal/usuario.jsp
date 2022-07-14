<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<div class="spinner-layer spinner-yellow">
		<div class="circle-clipper left">
			<div class="circle"></div>
		</div>
		<div class="gap-patch">
			<div class="circle"></div>
		</div>
		<div class="circle-clipper right">
			<div class="circle"></div>
		</div>
	</div>

	<div class="spinner-layer spinner-green">
		<div class="circle-clipper left">
			<div class="circle"></div>
		</div>
		<div class="gap-patch">
			<div class="circle"></div>
		</div>
		<div class="circle-clipper right">
			<div class="circle"></div>
		</div>
	</div>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbar-main-menu.jsp"></jsp:include>
					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">


										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">

													<div class="card-block">
														<h3 class="sub-title">Cadastro de usu�rio</h3>

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" class="form-control"
																	required="required" value="${modelLogin.nome}">
																<span class="form-bar"></span> <label
																	class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" class="form-control"
																	required="required" value="${modelLogin.email}">
																<span class="form-bar"></span> <label
																	class="float-label">E-mail:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="login" class="form-control"
																	required="required" value="${modelLogin.login}">
																<span class="form-bar"></span> <label
																	class="float-label">Login</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="password" name="footer-email"
																	class="form-control" required="required"
																	value="${modelLogin.senha}"> <span
																	class="form-bar"></span> <label class="float-label">Senha</label>
															</div>
															<button type="button" class="btn btn-secondary" onclick="limparForm();">Novo</button>
															<button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
															<button type="button" class="btn btn-danger"onclick="criarDelete();">Excluir</button>
															<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar</button>
														</form>

													</div>
												</div>
											</div>
										</div>

										<span>${msg}</span>
									</div>
									<!-- Page-body-end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="jsfile.jsp"></jsp:include>

<!-- Modal -->
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisar</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome" aria-label="nome" aria-describedby="basic-addon2" id="nomeBusca">
						<div class="input-group-append">
							<button class="btn btn-success" type="button" onclick="buscarUsuario();" >Buscar</button>
						</div>
					</div>
					
<table class="table" id="tabelaresultados">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Nome</th>
      <th scope="col">Ver</th>
    </tr>
  </thead>
</table>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	function buscarUsuario(){
		
		var nomeBusca = document.getElementById('nomeBusca').value;
			if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != ''){/*Valida��o banco*/
				
		var urlAction = document.getElementById('formUser').action;
				
		$.ajax({
					method : "get",
					url : urlAction,
					data : "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
					success : function(response) {
						alert(response.lenght);
					}
				
		var json = JSON.parse(response)
		
		$('#tabelaresultados > tr' ).remove();
		
		for(var p = 0; p < json.lenght; p++){
			$('#tabelaresultados > tr' ).apprend('<tr><td>'+json[p].id+'</td></tr>');
		}
		
		
		
		}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao buscar usu�rio por nome' + xhr.responseText);
						});
			}
		}

		function criarDeleteComAjax() {
			if (confirm('Deseja realmente excluir os dados?')) {

				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({

					method : "get",
					url : urlAction,
					data : 'id=' + idUser + '&acao=deletarajax',
					success : function(response) {
						alert(response);
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar os dados por id: '
									+ xhr.responseText)
						});
			}
		}

		function criarDelete() {
			if (confirm('Deseja realmente excluir os dados?')) {
				document.getElementById('formUser').method = 'get';
				document.getElementById('acao').value = 'deletar';
				document.getElementById('formUser').submit();
			}
		}

		function limparForm() {
			var elements = document.getElementById('formUser').elements;
		}
		for (p = 0; p < elements.length; p++) {
			elements[p].value = '';
		}
	</script>

</body>

</html>
