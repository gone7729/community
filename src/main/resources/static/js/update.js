const itemBtn = document.querySelector("#item-btn");
const optionItems = document.querySelectorAll(".option-item");
const itemBox = document.querySelector("#item-box");
const formCategory = document.querySelector("#category");


itemBtn.addEventListener("click", ()=> {
    if(itemBox.style.height == "0px"){
        itemBox.style. height = itemBox.scrollHeight + "px";
    }else{
        itemBox.style. height = "0px";
    }
});

optionItems.forEach((item) => {
  item.addEventListener("click", (e) => {
    itemBtn.firstElementChild.textContent = item.textContent;
    itemBox.style.height = "0px";
    formCategory.value = e.target.value;
  });
});

editor.addEventListener("click", ()=>{
    if(itemBox.style.height != "0px"){
        itemBox.style. height = "0px";
    }
})