package pojos;

public class HerOkuAppPostResponseBodyPojo {

    private Integer bookingid;
    private BookingPojo booking;

    public HerOkuAppPostResponseBodyPojo(Integer bookingId, BookingPojo booking) {
        this.bookingid = bookingId;
        this.booking = booking;
    }

    public HerOkuAppPostResponseBodyPojo() {
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "HerOkuAppPostResponseBodyPojo{" +
                "bookingId=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
