import ProductDetails from "./ProductDetails";

const Products = ({products}) => {
    return (
        <>
            {
                products.map((product, index) => (
                    <ProductDetails key={index} product={product} />
                ))
            }
        </>
    )
}

export default Products;