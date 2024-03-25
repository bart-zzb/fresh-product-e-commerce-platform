package cn.tedu.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable{

	private static final long serialVersionUID = 8556485044700393868L;
	private Integer id;
	private String name;
	private Integer parentId;

}
