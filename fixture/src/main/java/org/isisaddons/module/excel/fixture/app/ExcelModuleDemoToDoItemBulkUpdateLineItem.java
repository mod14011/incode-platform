/*
 *  Copyright 2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.module.excel.fixture.app;

import java.math.BigDecimal;
import org.isisaddons.module.excel.fixture.dom.ExcelModuleDemoToDoItem;
import org.isisaddons.module.excel.fixture.dom.ExcelModuleDemoToDoItem.Category;
import org.isisaddons.module.excel.fixture.dom.ExcelModuleDemoToDoItem.Subcategory;
import org.isisaddons.module.excel.fixture.dom.ExcelModuleDemoToDoItems;
import org.joda.time.LocalDate;
import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.annotation.ActionSemantics.Of;

@Named("Bulk update line item")
@Bookmarkable
public class ExcelModuleDemoToDoItemBulkUpdateLineItem
        extends AbstractViewModel 
        implements Comparable<ExcelModuleDemoToDoItemBulkUpdateLineItem> {


    public String title() {
        final ExcelModuleDemoToDoItem existingItem = getToDoItem();
        if(existingItem != null) {
            return "EXISTING: " + getContainer().titleOf(existingItem);
        }
        return "NEW: " + getDescription();
    }
    
    
    // //////////////////////////////////////
    // ViewModel implementation
    // //////////////////////////////////////
    

    @Override
    public String viewModelMemento() {
        return toDoItemExportImportService.mementoFor(this);
    }

    @Override
    public void viewModelInit(final String mementoStr) {
        toDoItemExportImportService.init(mementoStr, this);
    }

    
    // //////////////////////////////////////
    // ToDoItem (optional property)
    // //////////////////////////////////////
    
    private ExcelModuleDemoToDoItem toDoItem;

    @MemberOrder(sequence="1")
    public ExcelModuleDemoToDoItem getToDoItem() {
        return toDoItem;
    }
    public void setToDoItem(ExcelModuleDemoToDoItem toDoItem) {
        this.toDoItem = toDoItem;
    }
    public void modifyToDoItem(ExcelModuleDemoToDoItem toDoItem) {
        setToDoItem(toDoItem);
        setDescription(toDoItem.getDescription());
        setCategory(toDoItem.getCategory());
        setSubcategory(toDoItem.getSubcategory());
        setComplete(toDoItem.isComplete());
        setCost(toDoItem.getCost());
        setDueBy(toDoItem.getDueBy());
        setNotes(toDoItem.getNotes());
        setOwnedBy(toDoItem.getOwnedBy());
    }

    
    // //////////////////////////////////////
    // Description (property)
    // //////////////////////////////////////
    
    private String description;

    @MemberOrder(sequence="2")
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    // //////////////////////////////////////
    // Category and Subcategory (property)
    // //////////////////////////////////////

    private Category category;

    @MemberOrder(sequence="3")
    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    // //////////////////////////////////////

    private Subcategory subcategory;

    @MemberOrder(sequence="4")
    public Subcategory getSubcategory() {
        return subcategory;
    }
    public void setSubcategory(final Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    // //////////////////////////////////////
    // OwnedBy (property)
    // //////////////////////////////////////
    
    private String ownedBy;

    @MemberOrder(sequence="5")
    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(final String ownedBy) {
        this.ownedBy = ownedBy;
    }


    // //////////////////////////////////////
    // DueBy (property)
    // //////////////////////////////////////

    private LocalDate dueBy;

    @MemberOrder(sequence="6")
    public LocalDate getDueBy() {
        return dueBy;
    }

    public void setDueBy(final LocalDate dueBy) {
        this.dueBy = dueBy;
    }

    
    // //////////////////////////////////////
    // Complete (property), 
    // Done (action), Undo (action)
    // //////////////////////////////////////

    private boolean complete;

    @MemberOrder(sequence="7")
    public boolean isComplete() {
        return complete;
    }

    public void setComplete(final boolean complete) {
        this.complete = complete;
    }


    // //////////////////////////////////////
    // Cost (property), UpdateCost (action)
    // //////////////////////////////////////

    private BigDecimal cost;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(final BigDecimal cost) {
        this.cost = cost!=null?cost.setScale(2):null;
    }
    

    // //////////////////////////////////////
    // Notes (property)
    // //////////////////////////////////////

    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }


    // //////////////////////////////////////
    // apply
    // //////////////////////////////////////

    @ActionSemantics(Of.IDEMPOTENT)
    @Bulk
    public ExcelModuleDemoToDoItem apply() {
        ExcelModuleDemoToDoItem item = getToDoItem();
        if(item == null) {
            // description must be unique, so check
            item = toDoItems.findByDescription(getDescription());
            if(item != null) {
                getContainer().warnUser("Item already exists with description '" + getDescription() + "'");
            } else {
                // create new item
                // (since this is just a demo, haven't bothered to validate new values)
                item = toDoItems.newToDo(getDescription(), getCategory(), getSubcategory(), getDueBy(), getCost());
                item.setNotes(getNotes());
                item.setOwnedBy(getOwnedBy());
                item.setComplete(isComplete());
            }
        } else {
            // copy over new values
            // (since this is just a demo, haven't bothered to validate new values)
            item.setDescription(getDescription());
            item.setCategory(getCategory());
            item.setSubcategory(getSubcategory());
            item.setDueBy(getDueBy());
            item.setCost(getCost());
            item.setNotes(getNotes());
            item.setOwnedBy(getOwnedBy());
            item.setComplete(isComplete());
        }
        return bulkInteractionContext.getInvokedAs().isBulk()? null: item;
    }

    
    // //////////////////////////////////////
    // compareTo
    // //////////////////////////////////////

    @Override
    public int compareTo(ExcelModuleDemoToDoItemBulkUpdateLineItem other) {
        return this.toDoItem.compareTo(other.toDoItem);
    }

    
    // //////////////////////////////////////
    // injected services
    // //////////////////////////////////////
    
    @javax.inject.Inject
    private ExcelModuleDemoToDoItemBulkUpdateService toDoItemExportImportService;
    
    @javax.inject.Inject
    private ExcelModuleDemoToDoItems toDoItems;

    @javax.inject.Inject
    private Bulk.InteractionContext bulkInteractionContext;
}
