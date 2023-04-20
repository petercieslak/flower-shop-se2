class CartItemClass {
    constructor(picture, name, amount, price) {
        this.picture = picture;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    getPicture(){
        return this.picture;
    }

    getName(){
        return this.name;
    }

    getAmount(){
        return this.amount;
    }

    addAmount(){
        this.amount= this.amount + 1;
    }

    subtractAmount(){
        this.amount = this.amount - 1;
    }

    getPrice(){
        return this.price;
    }
}

export default CartItemClass