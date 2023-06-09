<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<!-- Product section-->
<section class="py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="row gx-4 gx-lg-5 align-items-center">
			<div class="col-md-6">
				<img id="mainImg" class="card-img-top mb-5 mb-md-0"
					src="https://dummyimage.com/600x700/dee2e6/6c757d.jpg" alt="..." />
			</div>
			<div class="col-md-6">
				<div class="small mb-1">SKU: BST-498</div>
				<h1 id="mainName" class="display-5 fw-bolder">Shop item
					template</h1>
				<div id="prices" class="fs-5 mb-5">
					<span class="text-decoration-line-through">$45.00</span> <span>$40.00</span>
				</div>
				<p id="mainContent" class="lead">Lorem ipsum dolor sit amet
					consectetur adipisicing elit. Praesentium at dolorem quidem modi.
					Nam sequi consequatur obcaecati excepturi alias magni, accusamus
					eius blanditiis delectus ipsam minima ea iste laborum vero?</p>
				<div class="d-flex">
					<input class="form-control text-center me-3" id="inputQuantity"
						type="num" value="1" style="max-width: 3rem" />
					<button class="btn btn-outline-dark flex-shrink-0" type="button">
						<i class="bi-cart-fill me-1"></i> Add to cart
					</button>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Related items section-->
<section class="py-5 bg-light">
	<div class="container px-4 px-lg-5 mt-5">
		<h2 class="fw-bolder mb-4">Related products(추천순위)</h2>
		<div
			class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"  id="cardTarget">
			
			<div class="col mb-5" id="recommendProduct" style="display:none;">
				<div class="card h-100">
					<!-- Sale badge-->
					<div class="badge bg-dark text-white position-absolute"
						style="top: 0.5rem; right: 0.5rem">Sale</div>
					<!-- Product image-->
					<img class="card-img-top"
						src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
					<!-- Product details-->
					<div class="card-body p-4">
						<div class="text-center">
							<!-- Product name-->
							<h5 class="fw-bolder">Special Item</h5>
							<!-- Product reviews-->
							<div
								class="d-flex justify-content-center small text-warning mb-2">
								<div class="bi-star-fill"></div>
								<div class="bi-star-fill"></div>
								<div class="bi-star-fill"></div>
								<div class="bi-star-fill"></div>
								<div class="bi-star-fill"></div>
							</div>
							<!-- Product price-->
							<span class="text-muted text-decoration-line-through">$20.00</span>
							<span>$18.00</span>
						</div>
					</div>
					<!-- Product actions-->
					<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
						<div class="text-center">
							<a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script>
 	fetch('getProduct.do',{
		method:'POST',
		headers:{
		  'Content-Type':'application/x-www-form-urlencoded'
		},
			body : 'productCode='+${param.productCode}
		})
	.then(resolve=>resolve.json())
	.then(result => {
		document.getElementById("mainImg").src="./shop/images/"+result.productName+".jpg";
		document.getElementById("mainName").innerText=result.productName;
		document.getElementById("prices").children[0]=result.price;
		document.getElementById("prices").children[1]=result.discountPrice;
		document.getElementById("mainContent").innerText=result.productContent;
		
	})
	.catch(err=>console.log(err)); 
 	
 	fetch('getRecommendList.do',{
		method:'POST',
		headers:{
		  'Content-Type':'application/x-www-form-urlencoded'
		},
			body : 'productCode='+${param.productCode}
		})
	.then(resolve=>resolve.json())
	.then(result=>{
		console.log(result);
		let div = document.getElementById('cardTarget');
		for(let i of result){
			let temp=document.getElementById('recommendProduct').cloneNode(true);
			temp.children[0].children[1].src="./shop/images/"+i.productName+".jpg";
			temp.children[0].children[2].children[0].children[0].innerText=i.productName;
			for(let j=0;j<(5-i.grade);j++){
				temp.children[0].children[2].children[0].children[1].children[j].remove();;
			}
			temp.children[0].children[2].children[0].children[2].innerText = i.price;
			temp.children[0].children[2].children[0].children[3].innerText = "$"+i.discountPrice;
			temp.style="display:block;"
			temp.addEventListener('click',function(){
				location.href="prodMain.do?productCode="+i.productCode;
			});
			div.append(temp);
		}
	})
	.catch(err=>console.log(err));
</script>
</html>
