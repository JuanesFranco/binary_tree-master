package com.binary_tree.binary_tree.model;

import com.binary_tree.binary_tree.exception.DataNotFoundException;
import com.binary_tree.binary_tree.exception.NTreeException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NNode {
    private Boy data;
    private List<NNode> children;

    public NNode(Boy data) {
        this.data = data;

    }


    public NNode findNTreeByIdentification(int identification)
    {
        if(this.getData().getIdentification()==identification)
        {
            return this;
        }
        else
        {
            if(this.children!=null) {
                for (NNode child : children) {
                    NNode nodeFind = child.findNTreeByIdentification(identification);
                    if (nodeFind != null) {
                        return nodeFind;
                    }
                }
            }

        }
        return null;

    }
}
