/*
 * openTCS copyright information:
 * Copyright (c) 2005-2011 ifak e.V.
 * Copyright (c) 2012 Fraunhofer IML
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.components.dialogs;

import static com.google.common.base.Preconditions.checkArgument;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import org.opentcs.guing.components.properties.type.StringSetProperty;
import org.opentcs.guing.model.AbstractConnectableModelComponent;
import org.opentcs.guing.model.elements.LocationModel;
import org.opentcs.guing.model.elements.LocationTypeModel;
import org.opentcs.guing.model.elements.PointModel;
import org.opentcs.guing.util.I18nPlantOverview;
import org.opentcs.guing.util.ResourceBundleUtil;

/**
 * Benutzeroberfläche zur Bearbeitung eines Fahrauftrags.
 *
 * @author Sebastian Naumann (ifak e.V. Magdeburg)
 */
public class EditDriveOrderPanel
    extends DialogContent {

  /**
   * Die zur Auswahl stehenden Stationen.
   */
  private final List<LocationModel> fLocations;
  /**
   * Die ausgewählte Station.
   */
  private AbstractConnectableModelComponent fSelectedLocation;
  /**
   * Die ausgewählte Aktion.
   */
  private String fSelectedAction;

  /**
   * Creates new form EditDriveOrderPanel
   *
   * @param locations die zur Auswahl stehenden Stationen
   */
  public EditDriveOrderPanel(List<LocationModel> locations) {
    initComponents();
    fLocations = sortLocations(locations);
    setDialogTitle(ResourceBundleUtil.getBundle(I18nPlantOverview.CREATETO_PATH)
        .getString("editDriverOrderPanel.create.title"));
  }

  /**
   * Creates new form EditDriveOrderPanel
   *
   * @param locations die zur Auswahl stehenden Stationen
   * @param location die Station
   * @param action die Aktion
   */
  public EditDriveOrderPanel(List<LocationModel> locations,
                             AbstractConnectableModelComponent location, String action) {
    checkArgument(location instanceof PointModel || location instanceof LocationModel,
                  String.format("Selected location has to be of type PointModel or LocationModel "
                      + "and not \"%s\".", location.getClass().getName()));
    initComponents();
    fLocations = sortLocations(locations);
    fSelectedLocation = location;
    fSelectedAction = action;
    setDialogTitle(ResourceBundleUtil.getBundle(I18nPlantOverview.CREATETO_PATH)
        .getString("editDriverOrderPanel.edit.title"));
  }

  /**
   * Sortiert eine Liste mit Stationen anhand des Namens.
   *
   * @param locations die zu sortierende Liste
   * @return die sortierte Liste
   */
  private List<LocationModel> sortLocations(List<LocationModel> locations) {
    Comparator<LocationModel> c = new Comparator<LocationModel>() {

      @Override
      public int compare(LocationModel o1, LocationModel o2) {
        String s1 = o1.getName().toLowerCase();
        String s2 = o2.getName().toLowerCase();
        return s1.compareTo(s2);
      }
    };

    List<LocationModel> result = new ArrayList<>(locations);
    Collections.sort(result, c);

    return result;
  }

  @Override
  public void update() {
  }

  @Override
  public void initFields() {
    for (LocationModel s : fLocations) {
      locationComboBox.addItem(s.getName());
    }

    if (fSelectedLocation != null) {
      locationComboBox.setSelectedItem(fSelectedLocation.getName());
    }
    else if (locationComboBox.getItemCount() > 0) {
      locationComboBox.setSelectedIndex(0);
    }

    if (fSelectedAction != null) {
      actionComboBox.setSelectedItem(fSelectedAction);
    }
  }

  /**
   * Liefert die ausgewählte Station.
   *
   * @return die ausgewählte Station
   */
  public Optional<LocationModel> getSelectedLocation() {
    int index = locationComboBox.getSelectedIndex();
    return index == -1 ? Optional.empty() : Optional.ofNullable(fLocations.get(index));
  }

  /**
   * Liefert die ausgewählte Aktion.
   *
   * @return die ausgewählte Aktion
   */
  public Optional<String> getSelectedAction() {
    return Optional.ofNullable((String) actionComboBox.getSelectedItem());
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    stationLabel = new javax.swing.JLabel();
    locationComboBox = new javax.swing.JComboBox<>();
    actionLabel = new javax.swing.JLabel();
    actionComboBox = new javax.swing.JComboBox<>();

    java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
    layout.columnWidths = new int[] {0, 5, 0};
    layout.rowHeights = new int[] {0, 5, 0};
    setLayout(layout);

    stationLabel.setFont(stationLabel.getFont());
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/org/opentcs/plantoverview/dialogs/createTransportOrder"); // NOI18N
    stationLabel.setText(bundle.getString("editDriverOrderPanel.label_location.text")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
    add(stationLabel, gridBagConstraints);

    locationComboBox.setFont(locationComboBox.getFont());
    locationComboBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        locationComboBoxActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    add(locationComboBox, gridBagConstraints);

    actionLabel.setFont(actionLabel.getFont());
    actionLabel.setText(bundle.getString("editDriverOrderPanel.label_action.text")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
    add(actionLabel, gridBagConstraints);

    actionComboBox.setFont(actionComboBox.getFont());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    add(actionComboBox, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents
  // CHECKSTYLE:ON

  /**
   * Aktualisiert den Inhalt der ComboBox mit den Aktionen. Wird aufgerufen,
   * wenn in der ComboBox mit den Stationen ein anderes Element ausgewählt
   * wurde.
   *
   * @param evt das auslösende Ereignis
   */
  private void locationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationComboBoxActionPerformed
    DefaultComboBoxModel<String> model
        = (DefaultComboBoxModel<String>) actionComboBox.getModel();
    model.removeAllElements();

    getSelectedLocation().ifPresent(location -> {
      LocationTypeModel type = location.getLocationType();
      StringSetProperty p = type.getPropertyAllowedOperations();
      for (String item : new ArrayList<>(type.getPropertyAllowedOperations().getItems())) {
        model.addElement(item);
      }

      if (model.getSize() > 0) {
        actionComboBox.setSelectedIndex(0);
      }
    });
  }//GEN-LAST:event_locationComboBoxActionPerformed

  // CHECKSTYLE:OFF
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<String> actionComboBox;
  private javax.swing.JLabel actionLabel;
  private javax.swing.JComboBox<String> locationComboBox;
  private javax.swing.JLabel stationLabel;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON
}
