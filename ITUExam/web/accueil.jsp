<%@page import="Model.serveur.Menu"%>
<%@page import="Model.services.DAO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Cuisine</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="css/zerogrid.css" type="text/css" media="all">
<link rel="stylesheet" href="css/responsive.css" type="text/css" media="all"> 


<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" media="all"> 
<link rel="stylesheet" href="js/bootstrap.min.js" type="text/css" media="all"> 
<link rel="stylesheet" href="css/perso.css" type="text/css" media="all"> 


<script type="text/javascript" src="js/jquery-1.6.js" ></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>  
<script type="text/javascript" src="js/Forum_400.font.js"></script>
<script type="text/javascript" src="js/atooltip.jquery.js"></script> 
 <script type="text/javascript" src="js/css3-mediaqueries.js"></script>
<!--[if lt IE 9]>
	<script type="text/javascript" src="js/html5.js"></script>
	<style type="text/css">
		.slider_bg {behavior:url(js/PIE.htc)}
	</style>
<![endif]-->
<!--[if lt IE 7]>
	<div style='clear:both;text-align:center;position:relative'>
		<a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" alt="" /></a>
	</div>
<![endif]-->
</head>
<body id="page2">
<div class="body6">
	<div class="body1">
		<div class="main zerogrid">
<!-- header -->
			<header>
				
				<div class="row">
					<div class="col-md-2 ">
						<h1><a href="index.html"><img src="images/kalitao.png" style="border-left: 2px solid white"/></a></h1>
					</div>
					<div class="col-md-10">

						<nav>
							<ul id="menu">
								<li  class="active"><a href="index.html">Menu</a></li>
								<li><a href="Cuisine.html">Faire une commande</a></li>
								<li><a href="Wine.html">Plats non livrés</a></li>
								<li><a href="CookBook.html">Plats cuits</a></li>
								<li><a href="CookBook.html">Addition</a></li>
								
								
								
							</ul>
						</nav>
					</div>
				</div>
			</header>
			
<!-- / header -->
<!-- content -->
			<article id="content">
				<div class="wrap">
					<section class="col-1-3"><div class="wrap-col">
						<div class="box">
							<div>
								<h2 class="letter_spacing">Nos <span>Catégories </span></h2>
								<select name="" id="" class="form-select">
									<option value="">a</option>
									<option value="">b</option>

								</select>
								<br>
								<a href="#" class="button1">Rechercher</a>
								
								
							</div>
						</div>
					</div></section>
					<section class="col-1-3"><div class="wrap-col">
						<% 
                                                    DAO dao = new DAO();
                                                    for(int i=0;i<dao.get_menu().getId_produit().length;i++){
                                                %>
                                                    <div class="box">
                                                            <div>
                                                                    <h2>Nos <span>Menu</span></h2>
                                                                    <figure><img src="images/page2_img1.jpg" alt="" ></figure>
                                                                    <p class="pad_bot1">Neque porro quisquam est, qui dolor- em ipsum qudolor sitamet consectetur adipisci velit, sed quia non numquam eius modi tempora incidunt.</p>
                                                                    <a href="#" class="button1">Read More</a>
                                                            </div>
                                                    </div>
                                                <% }%>
					</div></section>
				
					

					
				</div>
			</article>
		</div>
	</div>
</div>

<div class="body3">

		<div class="main zerogrid">
<!-- footer -->
			<footer>
				<div class="wrapper">
					<section class="col-2-3"><div class="wrap-col">
						<h3>Contact: <span>034 04 145 85</span></h3>
						Ouvert à partir de:  <a rel="nofollow" href="http://www.TemplateMonster.com/" target="_blank">8h à 20h</a> | <a href="https://www.zerotheme.com" title="free website templates">Andoharanofotsy</a>
					</div></section>
					<section class="col-1-3"><div class="wrap-col">
						<h3>Nous-suivre:</h3>
						<ul id="icons">
							<li><a href="#" class="normaltip" title="Facebook"><img src="images/icon1.gif" alt=""></a></li>
							<li><a href="#" class="normaltip" title="Twitter"><img src="images/icon3.gif" alt=""></a></li>
						</ul>
					</div></section>
				</div>
				<!-- {%FOOTER_LINK} -->
			</footer>
<!-- / footer -->
		</div>

</div>
<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>