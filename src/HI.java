import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;
public class HI {
public static void main(String[] args) {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    VideoCapture capture = new VideoCapture(0);
    Mat matrix = new Mat();
    capture.read(matrix);
    Rect rectCrop = new Rect(270,190,200,200);
    Mat croppedImage = matrix.submat(rectCrop);
    Mat ColorMat= new Mat();

    Mat hsv = croppedImage.clone();
//    Imgproc.cvtColor(croppedImage, hsv, Imgproc.COLOR_BGR2HSV);

    while (true) {
        Mat Colored = new Mat();
        capture.read(matrix);

        Imgproc.cvtColor(croppedImage, hsv, Imgproc.COLOR_BGR2HSV);
        Core.inRange(hsv, new Scalar(20, 135, 130), new Scalar(50, 255,255), hsv);
        Core.inRange(croppedImage, new Scalar(63,92,131),new Scalar(0,150,150), ColorMat);
        Core.add(croppedImage,croppedImage,Colored,hsv);
        int numPixles = Core.countNonZero(hsv);
        if (numPixles>0) {
            System.out.println("Yelp");
        }
        // HighGui.imshow("crop",croppedImage);
        //HighGui.imshow("mask", hsv);
        HighGui.imshow("colored", Colored);
        HighGui.waitKey(1);
    }
}
}


