const ProductDetails = ({product}) => {
    return (
        <div className="main-container">
            <div>Product Number </div>
            <div>{product.name}</div>
        </div>
    )
}

export default ProductDetails;