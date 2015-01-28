package ch17.se03.Senior;

public interface CrazyitProtocal {
	// 定义协议字符串的长度
	int PROTOCAL_LEN = 2;
	
	// 下面是一些协议字符串，服务器和客户端交换的信息都应该在前、后添加这种特殊字符
	String MSG_ROUND = "§γ";
	String USER_ROUND = "∏∑";
	String PRIVATE_ROUND = "★【";
	
	String LOGIN_SUCCESS = "1";
	String NAME_REP = "-1";
	
	String SPLIT_SIGN = "※";
	
}
