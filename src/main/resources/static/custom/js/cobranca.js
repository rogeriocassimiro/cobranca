$("#modalConfirmacao").on("show.bs.modal", function(event){
	var button = $(event.relatedTarget);
	
	var descricaoTitulo = button.data('descricao');
	var codigoTitulo = button.data('codigo');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith("/"))
		action+="/";
	
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>'+descricaoTitulo+'</strong>');
});

$(function(){
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal : ',', thousands : '.', allowZero : true});
	
	$('.js-atualizar-status').on('click', function(event){
		event.preventDefault();
		var btnReceber = $(event.currentTarget);
		var urlReceber = btnReceber.attr('href');
		
		var response = $.ajax({
			url : urlReceber,
			type : 'PUT'
		}).done(function(data){
			btnReceber.hide();
			var codigoTitulo = btnReceber.data('codigo');
			$('[data-role='+codigoTitulo+']').html('<span class="label label-success">'+data+'</span>');
		}).fail(function(data){
			console.log("Erro ao receber cobrança "+data);
		});
		
	});
});