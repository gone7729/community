const catBtn = document.querySelector("#category-btn");
const catBox = document.querySelector("#category-box");
const catItems = document.querySelectorAll(".category-item");
const main = document.getElementsByTagName("main");

catBtn.addEventListener("click", ()=> {
    if(catBox.style.height == "0px"){
        catBox.style.height = catBox.scrollHeight + "px";
    }else{
        catBox.style.height = "0px";
    }
});

catItems.forEach((item) => {
    item.addEventListener("click", () =>{
        if(catBtn.firstElementChild.textContent == "작성자" ){
            catBtn.firstElementChild.textContent = item.firstElementChild.textContent;
            item.firstElementChild.textContent = "작성자";
            catBox.style.height = "0px";
        }else if(catBtn.firstElementChild.textContent == "제목"){
            catBtn.firstElementChild.textContent = item.firstElementChild.textContent;
            item.firstElementChild.textContent = "제목";
            catBox.style.height = "0px";
        }else{
            catBtn.firstElementChild.textContent = item.firstElementChild.textContent;
            item.firstElementChild.textContent = "내용";
            catBox.style.height = "0px";
        }
    });
});






