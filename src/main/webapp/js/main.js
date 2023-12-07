//Chức năng chuyển đổi giày theo màu ở trang chủ phần Hot Sale
const ListColorShoes = document.querySelectorAll(".ellipse");
const imgOfShoes = document.querySelector(".trending-box .img img");
ListColorShoes.forEach((item) => {
  item.addEventListener("click", (e) => {
    if (item.id) {
      imgOfShoes.setAttribute("src", "./img/" + item.id + "Shoes.png");
    }
  });
});
//Chức năng chuyển đổi slide
let listSliderContainer = document.querySelectorAll(".slider-container");
let pos = 0;
function nextSlide() {
  listSliderContainer[pos].classList.remove("active");
  pos = (pos + 1) % listSliderContainer.length;
  listSliderContainer[pos].classList.add("active");
}
function previousSlide() {
  listSliderContainer[pos].classList.remove("active");
  pos = (pos - 1 + listSliderContainer.length) % listSliderContainer.length;
  listSliderContainer[pos].classList.add("active");
}
//chức năng chuyển đổi hình ảnh ở new product
let listImgFeatures1 = document.querySelectorAll(".imgFeature1");
listImgFeatures1.forEach((item) => {
  item.addEventListener("click", (e) => {
    let imgSrc = item.getAttribute("src");
    document.querySelector(".bigImg1").src = imgSrc;
  });
});
let listImgFeatures2 = document.querySelectorAll(".imgFeature2");
listImgFeatures2.forEach((item) => {
  item.addEventListener("click", (e) => {
    let imgSrc = item.getAttribute("src");
    document.querySelector(".bigImg2").src = imgSrc;
  });
});
let listImgFeatures3 = document.querySelectorAll(".imgFeature3");
listImgFeatures3.forEach((item) => {
  item.addEventListener("click", (e) => {
    let imgSrc = item.getAttribute("src");
    document.querySelector(".bigImg3").src = imgSrc;
  });
});
//chức năng chọn size đổi màu ở Best Seller
const listSizeOption = document.querySelectorAll(
  ".newProduct .sizeBoxoption .sizeOption"
);
function changeSize(size) {
  const sizeShoe = document.querySelector(
    ".newProduct .sizeBoxoption .sizeOption.size" + size
  );
  listSizeOption.forEach((item) => {
    item.classList.remove("active");
  });
  sizeShoe.classList.toggle("active");
}
const listColorOption = document.querySelectorAll(
  ".newProduct .colorBox .colorBoxOption .colorOption"
);
const listSmallImg = document.querySelectorAll(
  ".newProduct .imgBox .imgFeature1"
);
const mainImg = document.querySelector(
  ".newProduct .newProductBox .productImgContent img"
);
function changecolor(color) {
  let i = 1;
  const colorShoe = document.querySelector("#" + color);
  listColorOption.forEach((item) => {
    console.log(item);
    item.classList.remove("active");
  });
  colorShoe.classList.toggle("active");
  listSmallImg.forEach((item) => {
    item.src = "./img/product" + colorShoe.id + "/" + i + ".jpg";
    i++;
  });
  mainImg.src = "./img/product" + colorShoe.id + "/1.jpg";
}
// listSizeOption.forEach(item=>{
//     item.addEventListener("click",(e)=>{
//         e.target.classList.toggle("active")
//     })
// })
// chức năng open/close useroptions
function onOfUseroptions() {
  let useritem = document.querySelector(".user-dropdown");
  useritem.classList.toggle("active");
}
