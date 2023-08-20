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
        if(catBtn.firstElementChild.textContent == "Writer" ){
            catBtn.firstElementChild.textContent = item.firstElementChild.textContent;
            item.firstElementChild.textContent = "Writer";
            catBox.style.height = "0px";
        }else if(catBtn.firstElementChild.textContent == "Title"){
            catBtn.firstElementChild.textContent = item.firstElementChild.textContent;
            item.firstElementChild.textContent = "Title";
            catBox.style.height = "0px";
        }else{
            catBtn.firstElementChild.textContent = item.firstElementChild.textContent;
            item.firstElementChild.textContent = "Text";
            catBox.style.height = "0px";
        }
    });
});






