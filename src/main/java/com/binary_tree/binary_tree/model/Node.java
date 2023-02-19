package com.binary_tree.binary_tree.model;

import com.binary_tree.binary_tree.controller.dto.BoyGradeDTO;
import com.binary_tree.binary_tree.exception.BinaryTreeException;
import com.binary_tree.binary_tree.exception.DataNotFoundException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@ToString
public class Node {
    private Boy data;
    private Node left;
    private Node right;
    private int grade;

    public Node(Boy data) {
        this.data = data;
        this.grade=1;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public void addNode(Boy data) throws BinaryTreeException {
        if (data.getIdentification() < this.getData().getIdentification()) {
            if (this.getLeft() == null) {
                this.setLeft(new Node(data));
            } else {
                this.left.addNode(data);
            }
            this.calculateGrade();
        } else if (data.getIdentification() > this.getData().getIdentification()) {

            if (this.getRight() == null) {
                this.setRight(new Node(data));
            } else {

                this.right.addNode(data);
            }
            this.calculateGrade();
        } else {
            throw new BinaryTreeException("Ya existe un niño con esa identificación");
        }

    }

    public List<Boy> preOrden() {
        List<Boy> listBoys = new ArrayList<>();
        listBoys.add(this.getData());
        if (this.getLeft() != null) {
            listBoys.addAll(this.getLeft().preOrden());
        }
        if (this.getRight() != null) {
            listBoys.addAll(this.getRight().preOrden());
        }
        return listBoys;
    }

    public List<Boy> inOrden() {
        List<Boy> listBoys = new ArrayList<>();
        if (this.getLeft() != null) {
            listBoys.addAll(this.getLeft().inOrden());
        }
        listBoys.add(this.getData());
        if (this.getRight() != null) {
            listBoys.addAll(this.getRight().inOrden());
        }
        return listBoys;
    }

    public List<Boy> postOrden()
    {
        List<Boy> listBoys = new ArrayList<>();
        if (this.getLeft() != null) {
            listBoys.addAll(this.getLeft().postOrden());
        }
        if (this.getRight() != null) {
            listBoys.addAll(this.getRight().postOrden());
        }
        listBoys.add(this.getData());
        return listBoys;
    }

    public void prune()
    {
        if(this.getRight()!=null)
        {
            if(this.getRight().isLeaf())
            {
                this.setRight(null);
            }
            else
            {
                this.getRight().prune();
            }
        }
        if(this.getLeft()!=null)
        {
            if(this.getLeft().isLeaf())
            {
                this.setLeft(null);
            }
            else
            {
                this.getLeft().prune();
            }
        }
    }

    public int calculateGrade()
    {
        int gradeLeft= this.getLeft()!=null ? this.getLeft().getGrade():0;
        int gradeRight= this.getRight()!=null ? this.getRight().getGrade():0;
        this.grade= gradeLeft >= gradeRight ? 1+ gradeLeft: 1 + gradeRight;
        return this.grade;
    }

    public List<BoyGradeDTO> boysGrade() {
        List<BoyGradeDTO> listBoys = new ArrayList<>();
        listBoys.add(new BoyGradeDTO(this.getData(),this.grade));
        if (this.getLeft() != null) {
            listBoys.addAll(this.getLeft().boysGrade());
        }
        if (this.getRight() != null) {
            listBoys.addAll(this.getRight().boysGrade());
        }
        return listBoys;
    }

    public List<Boy> getBoysByLevel(int searchLevel, int yourLevel)
    {
           List<Boy> listBoysLevel= new ArrayList<>();
           if(this.isLeaf())
                return listBoysLevel;

           if(searchLevel == yourLevel+1)
           {

               if(this.getLeft()!=null)
               {
                   listBoysLevel.add(this.getLeft().getData());
               }
               if(this.getRight()!=null)
               {
                   listBoysLevel.add(this.getRight().getData());
               }
           }
           else
           {
               if(this.getLeft()!=null)
               {
                    listBoysLevel.addAll(this.getLeft().getBoysByLevel(searchLevel,yourLevel+1));
               }
               if(this.getRight()!=null)
               {
                   listBoysLevel.addAll(this.getRight().getBoysByLevel(searchLevel,yourLevel+1));
               }
           }
           return listBoysLevel;
    }


    public Boy findLargest()
    {
        if(this.getRight()==null)
        {
            return this.getData();
        }
        else
        {
            return this.getRight().findLargest();
        }
    }

    public Boy findSmallest()
    {
        if(this.getLeft()==null)
        {
            return this.getData();
        }
        else
        {
            return this.getLeft().findSmallest();
        }
    }

    public void deleteNode(int identificationToDelete) throws DataNotFoundException
    {
        if(identificationToDelete < this.data.getIdentification())
        {
            ///Preguntar a la izq
            if(this.getLeft()!=null)
            {
                if(this.getLeft().getData().getIdentification()==identificationToDelete)
                {
                    //ubicado en el papa del que debo eliminar
                    if(this.getLeft().isLeaf())
                    {
                        this.setLeft(null);
                    }
                    else if(this.getLeft().getLeft()==null && this.getLeft().getRight()!=null)
                    {
                        this.setLeft(this.getLeft().getRight());
                    }
                    else if(this.getLeft().getLeft()!=null && this.getLeft().getRight()==null)
                    {
                        this.setLeft(this.getLeft().getLeft());
                    }
                    else
                    {
                        ///Los dos estan llenos
                        //obtener el dato por el que lo voy a reemplazar
                        Boy dataReplace=null;
                        if(this.getLeft().getLeft().getGrade() <  this.getLeft().getRight().getGrade())
                        {
                            dataReplace= this.getLeft().getLeft().findLargest();
                        }
                        else
                        {
                            dataReplace= this.getLeft().getRight().findSmallest();
                        }
                        this.getLeft().deleteNode(dataReplace.getIdentification());
                        this.getLeft().setData(dataReplace);
                    }
                }
                else
                {
                    this.getLeft().deleteNode(identificationToDelete);
                }
            }
            else
            {
                throw new DataNotFoundException("El dato a eliminar no existe");
            }
        }
        else
        {
            //preguntar a la derecha
            if(this.getRight()!=null)
            {
                if(this.getRight().getData().getIdentification()==identificationToDelete)
                {
                    //ubicado en el papa del que debo eliminar
                    if(this.getRight().isLeaf())
                    {
                        this.setRight(null);
                    }
                    else if(this.getRight().getLeft()==null && this.getRight().getRight()!=null)
                    {
                        this.setRight(this.getRight().getRight());
                    }
                    else if(this.getRight().getLeft()!=null && this.getRight().getRight()==null)
                    {
                        this.setRight(this.getRight().getLeft());
                    }
                    else
                    {
                        ///Los dos estan llenos
                        //obtener el dato por el que lo voy a reemplazar
                        Boy dataReplace=null;
                        if(this.getRight().getLeft().getGrade() <  this.getRight().getRight().getGrade())
                        {
                            dataReplace= this.getRight().getLeft().findLargest();
                        }
                        else
                        {
                            dataReplace= this.getRight().getRight().findSmallest();
                        }
                        this.getRight().deleteNode(dataReplace.getIdentification());
                        this.getRight().setData(dataReplace);
                    }
                }
                else
                {
                    this.getRight().deleteNode(identificationToDelete);
                }
            }
            else
            {
                throw new DataNotFoundException("El dato a eliminar no existe");
            }
        }

    }
}
