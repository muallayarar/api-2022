package pojos;

public class DummyRestApiPojo {


        private String status;
        private DummyRestApiPojo data;
        private String message;


    public DummyRestApiPojo(String status, DummyRestApiPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public DummyRestApiPojo() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyRestApiPojo getData() {
        return data;
    }

    public void setData(DummyRestApiPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyRestApiPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
