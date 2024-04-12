package cafe.domain.product

class Americano : Product {
    override val price: Double
        get() = 1000.0
    override val factory: () -> Product
        get() = ::Americano
}
