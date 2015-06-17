/**
 * 
 */

	$(document).ready(function(){
		var contents_top_height = $('.contents_top').height();
		var contents_bottom_height;
		var side_flag=false;
		$('#main_contents').css('height',parseInt($(window).height()-55)+'px');
		$('.contents_bottom').css('height',parseInt($(window).height()-contents_top_height-55)+'px');
		contents_bottom_height = $('.contents_bottom').height();
		$('.contents_bottom .main_btn').css('line-height',parseInt(contents_bottom_height+230)+'px');
		$("#side_menu").css('height',parseInt($(window).height()-55)+'px');
		$("#side_menu_btn").click(function(){
			if(side_flag==false){
				$("#side_menu").show('slide',{
					direction:'right'
				},1000);
				side_flag=!side_flag;
			}else{
				$("#side_menu").hide('slide',{
					direction:'right'
				},1000);
				side_flag=!side_flag;
			}
			
		});
		$(".modal-dialog").css('top',200+"px");
		$("#join_btn").click(function(){
			$('body').css('padding',0);
		});
	});