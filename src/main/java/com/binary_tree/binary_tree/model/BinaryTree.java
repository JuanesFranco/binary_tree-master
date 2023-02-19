package com.binary_tree.binary_tree.model;

import com.binary_tree.binary_tree.controller.dto.BoyGradeDTO;
import com.binary_tree.binary_tree.exception.BinaryTreeException;
import com.binary_tree.binary_tree.exception.DataNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BinaryTree {
    private Node root;
    private int count;

    public void addNode(Boy data) throws BinaryTreeException
    {
        if(root==null)
        {
            root = new Node(data);
        }
        else
        {
            root.addNode(data);
        }
        count++;
    }

    public List<Boy> listBoys(int which) throws DataNotFoundException
    {
        if(root!=null)
        {
            List<Boy> listBoys= new ArrayList<>();
            switch(which)
            {
                case 1:
                    return root.preOrden();
                case 2:
                    return root.inOrden();
                case 3:
                    return root.postOrden();
            }
        }
        throw new DataNotFoundException("No hay datos que mostrar");
    }

    public boolean prune() throws DataNotFoundException
    {
        if(root!=null)
        {
            if(root.isLeaf())
            {
                root=null;
            }
            else
            {
                root.prune();
            }
            return true;
        }
        throw new DataNotFoundException("No hay datos para podar");

    }

    public List<BoyGradeDTO> getBoysGrade() throws DataNotFoundException
    {
        if(root!=null)
        {
            return root.boysGrade();
        }
        throw new DataNotFoundException("No hay datos que mostrar");
    }

    public List<Boy> getBoysByLevel(int searchLevel) throws DataNotFoundException,BinaryTreeException
    {
        if(root!=null)
        {
            if(searchLevel <= root.getGrade())
            {
                if(searchLevel==1)
                {
                    List<Boy> listBoysLevel = new ArrayList<>();
                    listBoysLevel.add(root.getData());
                    return listBoysLevel;
                }
                else
                {
                    return root.getBoysByLevel(searchLevel,1);
                }
            }
            throw new BinaryTreeException("El level ingresado no existe");
        }
        throw new DataNotFoundException("No hay datos que mostrar");
    }


    public Boy findLargest() throws DataNotFoundException
    {
        if(root!=null)
        {
            return root.findLargest();
        }
        throw new DataNotFoundException("No hay datos que mostrar");
    }

    public Boy findSmallest() throws DataNotFoundException
    {
        if(root!=null)
        {
            return root.findSmallest();
        }
        throw new DataNotFoundException("No hay datos que mostrar");
    }

    public void deleteNode(int identificationToDelete) throws DataNotFoundException
    {
        if(root!=null)
        {
            if(root.getData().getIdentification()==identificationToDelete)
            {
                Node temp= new Node(new Boy(identificationToDelete+1,"temp",(byte)2,"Manizales"));
                temp.setLeft(root);
                temp.deleteNode(identificationToDelete);
                root = temp.getLeft();
            }
            else
            {
                root.deleteNode(identificationToDelete);
            }
        }
        else
            throw new DataNotFoundException("No hay datos para eliminar");
    }

}
