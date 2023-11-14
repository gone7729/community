const ctgBtn = document.querySelector("#category-btn");
  const ctgBox = document.querySelector("#category-box");
  const ctgItems = document.querySelectorAll(".category-item");
  const ctgInput = document.querySelector("#search-ctg");

  ctgBtn.addEventListener("click", ()=> {
    if(ctgBox.style.height == "0px"){
        ctgBox.style.height = ctgBox.scrollHeight + "px";
    }else{
        ctgBox.style.height = "0px";
    }
  });

  ctgItems.forEach((item) =>{
    item.addEventListener("click", ()=>{
        console.log(item.getAttribute('value'));
        ctgBtn.textContent = item.textContent;
        ctgInput.value = item.getAttribute('value');
        console.log(ctgInput.value);
        ctgBox.style.height = "0px";
    })
  })