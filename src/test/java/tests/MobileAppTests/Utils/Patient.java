package tests.MobileAppTests.Utils;

public class Patient {
    public String patientName;
    public String mobile;
    public String gender;

    public Patient(String patientName, String mobile, String gender) {
        this.patientName = patientName;
        this.mobile = mobile;
        this.gender = gender;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }
}
