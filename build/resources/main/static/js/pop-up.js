
class PopUp {
    constructor() {
        this.popUpElement = null;
    }

    show(message, cancelText = '취소', confirmText = '확인') {
        this.create(message, cancelText, confirmText);
        document.body.appendChild(this.popUpElement);
    }

    create(message, cancelText, confirmText) {
        this.popUpElement = document.createElement('section');
        this.popUpElement.id = 'pop-up-id';
        this.popUpElement.className = 'pop-up';
        this.popUpElement.innerHTML = `
                    <h2 class="none">팝업 생성</h2>
                    <div class="pop-up">
                        <div class="pop-up__box">
                            <h3 class="pop-up__title">${message}</h3>
                            <div class="pop-up__btns">
                                <button class="pop-up__btn__cancel">${cancelText}</button>
                                <button class="pop-up__btn__confirm">${confirmText}</button>
                            </div>
                        </div>
                    </div>
                `;

        this.popUpElement.querySelector('.pop-up__btn__cancel').addEventListener('click', () => this.close());
        this.popUpElement.querySelector('.pop-up__btn__confirm').addEventListener('click', () => this.confirm());
    }

    close() {
        if (this.popUpElement) {
            this.popUpElement.remove();
            this.popUpElement = null;
        }
    }

    confirm() {
        document.getElementById('tag-delete-form-id').submit();
        this.close();
    }
}