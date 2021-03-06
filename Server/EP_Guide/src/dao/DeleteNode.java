package dao;

import java.util.ArrayList;
import java.util.List;

import constant.ConstantCode;
import tool.DataTransform;
import tool.OutputByteGrp;

public class DeleteNode {
	public static byte[] delete(byte[] msgbody) {
		// -- 解析消息体--//
		int readPos = 0;// 字符数组读指针初始化
		byte[] nodeNum = new byte[] { msgbody[readPos++], msgbody[readPos++] };
		List<String> nodeList = new ArrayList<String>();
		for (int i = 0; i < DataTransform.byteGroupToInt(nodeNum); i++) {
			byte[] nodeID = new byte[6];
			for (int k = 0; k < 6; k++) {
				nodeID[k] = msgbody[readPos++];
			}
			nodeList.add(DataTransform.bytesToString(nodeID));
		}

		String sqlexcu = null;
		DBHelper db = new DBHelper();
		byte[] result = ConstantCode.Failed;

		for (int i = 0; i < nodeList.size(); i++) {
			try {
				sqlexcu = "Delete From parking_spaces where pSpace_ID=? And garage_ID=? ";
				db.preState = db.con.prepareStatement(sqlexcu);
				db.preState.setString(1, nodeList.get(i));
				db.preState.setString(2, "1");// 默认1号库
				int isSucs = db.preState.executeUpdate();
				System.out.println("isSuce:" + isSucs);
				System.out.println("要删除的节点ID:" + nodeList.get(i));
				if (isSucs > 0) {
					result = ConstantCode.Succeed;
					OutputByteGrp.output(result);
					System.out.println("删除节点成功 ：" + nodeList.get(i));
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Abnormal , DB : 删除节点时插入数据库异常： ");
				e.printStackTrace();
				return result;
			}
		}
		try {
			db.sql.close();
			db.con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Abnormal , DB : 删除节点时关闭数据库异常： ");
			e.printStackTrace();
		}

		return result;
	}
}
