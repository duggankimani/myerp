package com.duggankimani.app.server.handlerutils;

import java.util.Enumeration;

import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.duggankimani.app.shared.model.MenuFolder;
import com.duggankimani.app.shared.model.MenuType;

public class TreeHelper {

	/**
	 * Creates a menu tree
	 */
	public static MenuFolder getMenu() {
		MenuFolder root = new MenuFolder();
		MTree mTree = getTree();
		MTreeNode rootNode = mTree.getRoot();
		generateMenu(root, rootNode);

		return root;
	}

	/**
	 * This method generate a the front-end menu tree
	 * 
	 * @param root
	 * @param mNode
	 */
	public static void generateMenu(MenuFolder root, MTreeNode mNode) {
		@SuppressWarnings("unchecked")
		Enumeration<MTreeNode> nodeEnum = mNode.children();

		while (nodeEnum.hasMoreElements()) {

			MTreeNode mChildNode = (MTreeNode) nodeEnum.nextElement();

			MenuFolder treeFolder = new MenuFolder();
			treeFolder.setName(mChildNode.getName());
			treeFolder.setDescription(mChildNode.getDescription());
			treeFolder.setId(mChildNode.getNode_ID());

			if (mChildNode.getChildCount() != 0) {
				generateMenu(treeFolder, mChildNode);
				root.addChildMenu(treeFolder);
			} else {
				MenuType menuType = null;

				if (mChildNode.isWindow()) {
					menuType = MenuType.WINDOW;
				} else if (mChildNode.isReport()) {
					menuType = MenuType.REPORT;
				} else if (mChildNode.isProcess()) {
					menuType = MenuType.PROCESS;
				} else {
					menuType = MenuType.UNSUPPORTED;
				}

				treeFolder.setMenuType(menuType);
				root.addChildMenu(treeFolder);
			}
		}

	}

	public static MTree getTree() {

		int adRoleId = Env.getAD_Role_ID(Env.getCtx());
		int adTreeId = getTreeId(adRoleId);

		MTree mTree = new MTree(Env.getCtx(), adTreeId, false, true, null);

		return mTree;

	}

	/**
	 * 
	 * @param adRoleId
	 * @return
	 */
	public static int getTreeId(int adRoleId) {
		int AD_Tree_ID = 0;
		AD_Tree_ID = DB
				.getSQLValue(
						null,
						"SELECT COALESCE(r.AD_Tree_Menu_ID, ci.AD_Tree_Menu_ID)"
								+ "FROM AD_ClientInfo ci"
								+ " INNER JOIN AD_Role r ON (ci.AD_Client_ID=r.AD_Client_ID) "
								+ "WHERE AD_Role_ID=?", adRoleId);
		if (AD_Tree_ID <= 0)
			AD_Tree_ID = 10; // Menu
		return AD_Tree_ID;
	}

}
