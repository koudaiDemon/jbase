import java.text.SimpleDateFormat;
import java.util.Date;

date = new Date();
sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

System.out.println(sdf.format(date));

return sdf.format(date);