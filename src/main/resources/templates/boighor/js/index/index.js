// let routePath = "http://localhost:9000"
//
// function laySachMoi() {
//
//     $.ajax({
//         url: routePath + '/api/v1/sach-moi',
//         method: 'GET',
//         dataType: 'json',
//         success: function(response) {
//                 for (var i = 0; i < 8; i++) {
//                     var sach = response.data
//                     var element = `<div class="product product__style--3">
//                     <div class="product__thumb">
//                         <a class="first__img" href="/api/v1/single-product"><img src="/images/books/1.jpg"
//                                                                                       alt="product image"></a>
//                         <div class="hot__box">
//                             <span class="hot-label">BEST SALLER</span>
//                         </div>
//                     </div>
//                     <div class="product__content content--center">
//                         <h4 value="data.tenSach"><a href="single-product.html">robin parrish</a></h4>
//                         <ul class="price d-flex">
//                             <li value="$P{sach.giaTien}">$35.00</li>
//                         </ul>
//
//                         <div class="product__hover--content">
//                             <ul class="rating d-flex">
//                                 <li class="on"><i class="fa fa-star-o"></i></li>
//                                 <li class="on"><i class="fa fa-star-o"></i></li>
//                                 <li class="on"><i class="fa fa-star-o"></i></li>
//                                 <li><i class="fa fa-star-o"></i></li>
//                                 <li><i class="fa fa-star-o"></i></li>
//                             </ul>
//                         </div>
//                     </div>
//                 </div>`
//                     $(".san-pham-moi").append(element)
//                 }
//         },
//         error: function(xhr, status, error) {
//             console.log(error);
//         }
//     });
// }
//
// laySachMoi()
