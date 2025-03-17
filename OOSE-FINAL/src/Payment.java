public class Payment {
    private Booking booking;
    private double amount;

    public Payment(Booking booking, double amount) {
        this.booking = booking;
        this.amount = amount;
    }

    public boolean processPayment(Member member) {
        if (member.getBalance() < amount) {
            System.out.println("[ERROR] Insufficient balance. Please add more funds.");
            return false;
        }
        member.deductBalance(amount);
        booking.confirm(); // Confirm the booking after successful payment
        System.out.println("✅ Payment successful! Booking confirmed.");
        return true;
    }

    public void refund(Member member) {
        member.addBalance(amount);
        booking.cancel(); // Cancel the booking and free seats upon refund
        System.out.println("💰 Refund processed. " + amount + " Baht returned to balance.");
    }
}
