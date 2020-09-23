(function($) {
	$(function() {
		var $window = $(window);

		$window.on('load', function() {
			var $thumbs = $('.box');
			
			$thumbs.hover(
				function() {
				  var newValue = $("img", this).attr("src").replace('/min/', '/min_m/')
				  $("img", this).attr("src", newValue);
				},
				function() {
				  var newValue = $("img", this).attr("src").replace('/min_m/', '/min/')
				  $("img", this).attr("src", newValue);
				}
			);
		});

		$('form').submit(function( event ) {
		  window.location.href = $(this).find('select').val() + ".html";
		  event.preventDefault();
		});
	});
})(jQuery);