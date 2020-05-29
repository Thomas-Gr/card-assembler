<?php
	function showCard($card) {
	  echo '
		<div class="box">
			<a href="viewer.php?card=' . $card['id'] .'" class="image fit"><img src="images/min/' . $card['id'] .'.jpg" alt="' . $card['name'] .'" /></a>
			<div class="inner">
				<h3>' . $card['name'] .'</h3>
				<p>Artist: <a href="index.php?type=artist&id=' . $card['idArtist'] . '">' . $card['artist'] .'</a></p>
				<a href="viewer.php?card=' . $card['id'] .'" class="button fit">Open mosaic</a>
			</div>
		</div>
		';
	}
?>