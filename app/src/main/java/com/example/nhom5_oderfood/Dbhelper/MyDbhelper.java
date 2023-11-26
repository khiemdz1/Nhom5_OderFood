package com.example.nhom5_oderfood.Dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbhelper extends SQLiteOpenHelper {
    static final String DB_NAME = "OrderFood.db";
    static final int DB_VERSION = 1;

    public MyDbhelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      // bảng khách hàng
        String Tablekhachhang = "CREATE TABLE KhachHang(" +
                "MaKH INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "Fullname TEXT NOT NULL," +
                "Sdt TEXT NOT NULL," +
                "Diachi TEXT NOT NULL)";
        db.execSQL(Tablekhachhang);
        // bảng loại món ăn
        String TableTheloai = "CREATE TABLE TheLoai(" +
                "MaTL INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Tentheloai TEXT NOT NULL)";
        db.execSQL(TableTheloai);
//        // bảng món ăn
        String Tablemonan = "CREATE TABLE MonAn(" +
                "MaMA INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Hinhanh TEXT NOT NULL," +
                "Tenmonan TEXT NOT NULL," +
                "MaTL INTEGER REFERENCES TheLoai(MaTL)," +
                "Dongia INTEGER NOT NULL," +
                "Mota TEXT NOT NULL)";
        db.execSQL(Tablemonan);

        // Bảng Hóa Đơn
        String Tablehoadon = "CREATE TABLE HoaDon(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MaHD TEXT NOT NULL," +
                "TenHD TEXT NOT NULL," +
                "SdtHD TEXT NOT NULL," +
                "DiachiHD TEXT NOT NULL," +
                "TenmonanHD TEXT NOT NULL," +
                "SoluongHD TEXT NOT NULL," +
                "NgaydatHD TEXT NOT NULL," +
                "GiaHD TEXT NOT NULL," +
                "MaKH INTEGER," +
                "FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH))";
        db.execSQL(Tablehoadon);
        // Bảng nhà cung cấp
        String Tablenhacc = "CREATE TABLE NhaCungCap(" +
                "MaNCC INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Tennhacc TEXT NOT NULL," +
                "Thongtin TEXT NOT NULL," +
                "Lienhe TEXT NOT NULL," +
                "Email TEXT NOT NULL)";
        db.execSQL(Tablenhacc);
//
        db.execSQL("INSERT INTO MonAn VALUES (1,'anhmonan1','Pizza Phô Mai',1,45000,'Pizza là loại bánh có hình tròn được nướng lên với 3 thành phần chính là đế bánh, nhân bánh và phô mai phủ. Trong đó, đế bánh được chế biến từ quá trình ủ bột mì, nấm men và nước, sau đó được nhào nặn thành hình tròn. Phần topping pizza đa dạng gồm hải sản, xúc xích, phô mai, ….')," +
                "(2,'anhmonan2','Gà Rán',1,56000,'Gà rán là một món ăn xuất xứ từ miền Nam Hoa Kỳ; nguyên liệu chính là những miếng thịt gà đã được lăn bột rồi chiên trên chảo, chiên ngập dầu, chiên áp suất hoặc chiên chân không. Lớp bột chiên xù sẽ giúp cho miếng gà có một lớp vỏ ngoài giòn rụm, còn phần thịt bên trong vẫn mềm và mọng nước.')," +
                "(3,'anhmonan3','Cơm Tấm',1,62000,'Cơm tấm là món ăn truyền thống của người Việt Nam. Taste Atlas mô tả đây là món ăn bình dân bởi nguyên liệu chính là tấm, một loại gạo vỡ trong quá trình sản xuất gạo. Đây là món ăn thực khách có thể tìm thấy ở bất kỳ đâu tại các tỉnh thành miền Tây Nam Bộ.')," +
                "(4,'anhmonan4','Hamburger',1,77000,'Hamburger (tiếng Việt đọc là hăm-bơ-gơ hay hem-bơ-gơ, tiếng Anh:/ˈhæmbɜrɡər/, tiếng Đức: /ˈhɛmˌbœːɐ̯ɡɐ/ hoặc /ˈhamˌbʊʁɡɐ/) là một loại thức ăn bao gồm bánh mì kẹp thịt xay (thường là thịt bò) ở giữa. Miếng thịt có thể được nướng, chiên, hun khói hay nướng trên lửa.')," +
                "(5,'nuocuong1','CoCa CoLa',2,12000,'CoCa Là loại nước ngọt được nhiều người yêu thích với hương vị thơm ngon, sảng khoái. Nước ngọt Coca Plus là dòng sản phẩm nước uống có ga không đường, bổ sung thêm chất dinh dưỡng. Lưu trữ, bảo quản: - Bảo quản nơi thoáng mát, tránh ánh nắng trực tiếp.')," +
                "(6,'nuocuong2','Pepsi',2,12000,'Pepsi một đồ uống giải khát có gas, lần đầu tiên được sản xuất bởi Caleb Bradham. Ban đầu, Ông pha chế ra một loại nước uống dễ hấp thụ làm từ nước cacbonat, đường, vani và một ít dầu ăn dưới tên “Nước uống của Brad\" năm 1892.')");
        db.execSQL("INSERT INTO KhachHang VALUES (1,'admin','admin','Cấn Gia Khiêm',0372858655,'BinhPhu,ThachThat,HaNoi')");

        db.execSQL("Insert Into TheLoai values(1,'Đồ ăn'),(2,'Đồ uống')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String tablekhachhang = "drop table if exists KhachHang";
        db.execSQL(tablekhachhang);
        String tableloaimonan = "drop table if exists TheLoai";
        db.execSQL(tableloaimonan);
        String tablemonan = "drop table if exists MonAn";
        db.execSQL(tablemonan);
        String tablehoadon = "drop table if exists HoaDon";
        db.execSQL(tablehoadon);
        String tablenhacc= "drop table if exists NhaCungCap";
        db.execSQL(tablenhacc);

        onCreate(db);
    }
}
