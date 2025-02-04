from typing import Union
from fastapi import FastAPI
from mysql.connector import Error
import json
import db

app = FastAPI()


@app.get("/dssv")
def get_danh_sach_sinh_vien():
    conn = db.connect_to_database()
    if not isinstance(conn, Error):
        cursor = conn.cursor()
        sql = "SELECT * FROM sinh_vien"
        cursor.execute(sql)
        rows = cursor.fetchall()
        data = []
        for row in rows:
            data.append(
                {
                    "mssv": row[0],
                    "mat_khau": row[1],
                    "ho_ten": row[2],
                    "ngay_sinh": row[3].strftime("%Y/%m/%d"),
                    "gioi_tinh": str(row[4]),
                    "cccd": row[5],
                    "sdt": row[6],
                    "noi_sinh": row[7],
                    "dia_chi": row[8],
                }
            )
        # Đóng con trỏ và kết nối
        cursor.close()
        conn.close()
        return {"dssv": data}
    else:
        print(f"Lỗi kết nối: {conn}")


@app.get("/sv/{mssv}")
def get_sinh_vien(mssv: str):
    conn = db.connect_to_database()
    if not isinstance(conn, Error):
        cursor = conn.cursor()
        sql = "SELECT * FROM sinh_vien WHERE mssv=%s"
        adr = (mssv,)
        cursor.execute(sql, adr)
        rows = cursor.fetchall()
        if len(rows) > 0:
            data = []
            data.append(
                {
                    "mssv": rows[0][0],
                    "mat_khau": rows[0][1],
                    "ho_ten": rows[0][2],
                    "ngay_sinh": rows[0][3].strftime("%Y/%m/%d"),
                    "gioi_tinh": str(rows[0][4]),
                    "cccd": rows[0][5],
                    "sdt": rows[0][6],
                    "noi_sinh": rows[0][7],
                    "dia_chi": rows[0][8],
                }
            )
            # Đóng con trỏ và kết nối
            cursor.close()
            conn.close()
            return {"sv": data}
        else:
            # Đóng con trỏ và kết nối
            conn.close()
            return {"message": "Không tìm thấy dữ liệu."}
    else:
        print(f"Lỗi kết nối: {conn}")


@app.post("/themsv")
def add_sinh_vien(
    mssv: str,
    mat_khau: str,
    ho_ten: str,
    ngay_sinh: str,
    gioi_tinh: str,
    cccd: str,
    sdt: str,
    noi_sinh: str,
    dia_chi: str,
):
    conn = db.connect_to_database()
    if not isinstance(conn, Error):
        cursor = conn.cursor()
        sql = """INSERT INTO sinh_vien (mssv,mat_khau,ho_ten,ngay_sinh,gioi_tinh,cccd,sdt,noi_sinh,dia_chi) 
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"""
        adr = (
            mssv,
            mat_khau,
            ho_ten,
            ngay_sinh,
            gioi_tinh,
            cccd,
            sdt,
            noi_sinh,
            dia_chi,
        )

        try:
            cursor.execute(sql, adr)
            conn.commit()
            # Đóng con trỏ và kết nối
            cursor.close()
            conn.close()
            return {"message": "Thêm sinh viên thành công."}
        except Error as e:
            # Đóng con trỏ và kết nối
            conn.close()
            return {"message": "Thêm sinh viên thất bại:" + str(e)}
    else:
        print(f"Lỗi kết nối: {conn}")