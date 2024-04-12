package cafe.domain.product

class CafeLatte : Product {
    override val price: Double
        get() = 2000.0
    override val factory: () -> Product
        get() = ::CafeLatte
}
