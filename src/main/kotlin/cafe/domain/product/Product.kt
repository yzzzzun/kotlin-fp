package cafe.domain.product

interface Product {
    val price: Double
    val factory: () -> Product
}
