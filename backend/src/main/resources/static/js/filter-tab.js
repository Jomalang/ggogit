document.addEventListener("DOMContentLoaded", () => {
    const filterTabBtn = document.getElementById("filter-tab-btn");
    const filterTabCloseBtn = document.getElementById("filter-tab-close-btn");

    const filterBg = document.getElementById("filter-bg-blur");
    const filterTab = document.getElementById("filter-tab");

    function filterNameUpdate(v1,v2){
        return`
        <div class="filter-tree-leaf__card-sort" id="filter-tree-leaf__card-sort">
        <input
                class="bar-search-current__detail-input"
                type="checkbox"
                id="bar-search-current__detail"
        />
        <label
                class="bar-search-current__detail"
                for="bar-search-current__detail"
        >${v1.value} ${v2.value}</label>
      </div>
        `;
    }


    let filterListener = function () {
        let radios = document.querySelectorAll("input[name='filter']");
        const txt = document.querySelector("#txt");
        let standard, sort;
        radios.forEach((radio) => {
            radio.addEventListener("change", (e) => {
                const current = e.currentTarget;
                if(current.checked){
                    standard = e.currentTarget;
                }
            });
        });

        radios = document.querySelectorAll("input[name='sort']");
        radios.forEach((radio) => {
            radio.addEventListener("change", (e) => {
                const current = e.currentTarget;
                if(current.checked){
                    sort = e.currentTarget;
                }
            });
        });

        return filterNameUpdate(standard,sort);
    }

    filterTabBtn.addEventListener("click", () => {
        filterBg.classList.remove("none");
        setTimeout(() => filterTab.classList.add("up"), 10);
    })

    filterTabCloseBtn.addEventListener("click", () => {
        filterTab.classList.remove("up");
        filterTab.addEventListener("transitionend", function handler() {
            filterBg.classList.add("none");
            filterTab.removeEventListener("transitionend", handler);
            filterListener();
        });
    })
})
