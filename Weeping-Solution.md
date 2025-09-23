# Building a Pollen Tracker App with React Native

Creating a React Native application is an exciting journey. In this guide, we will build a Pollen Tracker app that displays pollen levels for various categories like trees, grass, weeds, and mold. We will also include functionality for users to reset their location. 

## Step 1: Setting Up the Environment

Ensure you have Node.js and npm installed. Then, create a new project with expo.

```bash
npx create-expo-app weepy
```

Navigate to your project directory:

```bash
cd weepy 
```

## Step 2: Installing Dependencies

Next, install the required dependencies. We will use `@rneui/themed` for theming and `react-native-elements` for icons.

```bash
@npx expo install react-dom react-native-web @expo/metro-runtime
npm install rneui/themed react-native-elements
```

## Step 3: Setting Up the Theme

We'll create a custom theme using `@rneui/themed`. This theme will define our primary and secondary colors for both light and dark modes.

```javascript
import React from "react";
import { View, StyleSheet } from "react-native";
import { ThemeProvider, createTheme } from "@rneui/themed";
import HomeScreen from "./src/screens/HomeScreen";

const theme = createTheme({
  lightColors: {
    primary: "#210124",
    secondary: "#CDC6AE",
    background: "#A3B4A2",
  },
  darkColors: {
    primary: "#252F40",
    secondary: "#187795",
  },
});

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <View style={styles.container}>
        <HomeScreen />
      </View>
    </ThemeProvider>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});

export default App;
```

## Step 4: Creating the Home Screen

The `HomeScreen` component will be our main display screen. This component will fetch pollen data based on the user's location.

```javascript
import React, { useEffect, useState } from "react";
import { View, Text, StyleSheet, TouchableOpacity } from "react-native";
import { Icon } from "react-native-elements";
import { useTheme } from "@rneui/themed";
import PollenDisplay from "../components/PollenDisplay";
import {
  getCurrentLocation,
  fetchCityCode,
  fetchPollenData,
  getLocationCoordinates,
} from "../utils/locationUtil";
import LocationResetModal from "../components/LocationResetModal";

const HomeScreen = () => {
  const { theme } = useTheme();
  const [location, setLocation] = useState<string>("");
  const [cityCode, setCityCode] = useState<string | null>(null);
  const [isModalVisible, setModalVisible] = useState<boolean>(false);
  
  const [treeCategory, setTreeCategory] = useState<string>("");
  const [grassCategory, setGrassCategory] = useState<string>("");
  const [weedCategory, setWeedCategory] = useState<string>("");
  const [moldCategory, setMoldCategory] = useState<string>("");
  
  const [treeValue, setTreeValue] = useState<number | null>(null);
  const [grassValue, setGrassValue] = useState<number | null>(null);
  const [weedValue, setWeedValue] = useState<number | null>(null);
  const [moldValue, setMoldValue] = useState<number | null>(null);
  
  const [treeColor, setTreeColor] = useState<string>("blue");
  const [grassColor, setGrassColor] = useState<string>("blue");
  const [weedColor, setWeedColor] = useState<string>("blue");
  const [moldColor, setMoldColor] = useState<string>("blue");
  
  const [treePosition, setTreePosition] = useState<string>("2%");
  const [grassPosition, setGrassPosition] = useState<string>("2%");
  const [weedPosition, setWeedPosition] = useState<string>("2%");
  const [moldPosition, setMoldPosition] = useState<string>("2%");

  const fetchLocationAndPollenData = async (newLocation: string) => {
    try {
      const apiKey = process.env.EXPO_PUBLIC_API_KEY as string;

      let coords = await getLocationCoordinates(newLocation);

      const { cityCode, location } = await fetchCityCode(coords, apiKey);
      setCityCode(cityCode);
      setLocation(location);

      const pollenData = await fetchPollenData(cityCode, apiKey);

      setTreeCategory(pollenData[4].Category);
      setGrassCategory(pollenData[1].Category);
      setWeedCategory(pollenData[3].Category);
      setMoldCategory(pollenData[2].Category);
      setTreeValue(pollenData[4].Value);
      setGrassValue(pollenData[1].Value);
      setWeedValue(pollenData[3].Value);
      setMoldValue(pollenData[2].Value);

      const changeTextColor = (category: string, catText: string) => {
        switch (category) {
          case "Good":
          case "Low":
            catText("#187795");
            break;
          case "Moderate":
            catText("#CD8B76");
            break;
          case "High":
            catText("#D2575E");
            break;
          case "Unhealthy":
            catText("#D62246");
            break;
          case "Hazardous":
            catText("Orange");
            break;
          default:
            break;
        }
      };

      changeTextColor(pollenData[4].Category, setTreeColor);
      changeTextColor(pollenData[1].Category, setGrassColor);
      changeTextColor(pollenData[3].Category, setWeedColor);
      changeTextColor(pollenData[2].Category, setMoldColor);

      const pollenAnimateIndicator = (
        category: string,
        catPosition: React.Dispatch<React.SetStateAction<string>>,
      ) => {
        switch (category) {
          case "Good":
            catPosition("5%");
            break;
          case "Low":
            catPosition("25%");
            break;
          case "Moderate":
            catPosition("50%");
            break;
          case "High":
            catPosition("70%");
            break;
          case "Unhealthy":
            catPosition("85%");
            break;
          case "Hazardous":
            catPosition("90%");
            break;
          default:
            break;
        }
      };

      pollenAnimateIndicator(pollenData[4].Category, setTreePosition);
      pollenAnimateIndicator(pollenData[1].Category, setGrassPosition);
      pollenAnimateIndicator(pollenData[3].Category, setWeedPosition);
      pollenAnimateIndicator(pollenData[2].Category, setMoldPosition);
    } catch (error) {
      console.log(error);
      setLocation("Unable to determine current location");
    }
  };

  const getCurrentLocationAndFetchData = async () => {
    try {
      const coords = await getCurrentLocation();
      if (coords) {
        const newLocation = `${coords.latitude},${coords.longitude}`;
        fetchLocationAndPollenData(newLocation);
      } else {
        setLocation("Unable to determine current location");
      }
    } catch (error) {
      console.log(error);
      setLocation("Unable to determine current location");
    }
  };

  useEffect(() => {
    getCurrentLocationAndFetchData();
  }, []);

  const updateLocation = (newLocation: string) => {
    setLocation(newLocation);
    fetchLocationAndPollenData(newLocation);
  };

  return (
    <View style={[{ backgroundColor: theme.colors.background }, styles.container]}>
      <TouchableOpacity style={styles.search} onPress={() => setModalVisible(true)}>
        <Icon name="search" type="font-awesome" />
      </TouchableOpacity>

      <Text style={[{ color: theme.colors.primary }, styles.locationText]}>
        {location}
      </Text>

      <View style={styles.displayWrap}>
        <View style={styles.displayGroup}>
          <PollenDisplay
            type="Trees"
            icon={<Icon name="tree" type="font-awesome" />}
            pollenValue={treeValue}
            pollenCat={treeCategory}
            catColor={treeColor}
            indicatorPosition={treePosition}
          />
          <PollenDisplay
            type="Weeds"
            icon={<Icon name="fire" type="font-awesome" />}
            pollenValue={weedValue}
            pollenCat={weedCategory}
            catColor={weedColor}
            indicatorPosition={weedPosition}
          />
        </View>

        <View style={styles.displayGroup}>
          <PollenDisplay
            type="Grass"
            icon={<Icon name="grass" type="material" />}
            pollenValue={grassValue}
            pollenCat={grassCategory}
            catColor={grassColor}
            indicatorPosition={grassPosition}
          />
          <PollenDisplay
            type="Mold"
            icon={<Icon name="bug" type="material-community" />}
            pollenValue={moldValue}
            pollenCat={moldCategory}
            catColor={moldColor}
            indicatorPosition={moldPosition}
          />
        </View>
      </View>

      <LocationResetModal
        visible={isModalVisible}
        onClose={() => setModalVisible(false)}
        onUpdateLocation={(newLocation: string) => updateLocation(newLocation)}
      />
    </

View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: "3%",
    alignItems: "stretch",
    paddingTop: "20%",
    justifyContent: "space-evenly",
  },
  search: {
    position: "absolute",
    right: 5,
    top: 35,
  },
  locationText: {
    fontSize: 45,
    textAlign: "center",
  },
  displayWrap: {
    flexDirection: "column",
    justifyContent: "space-evenly",
  },
  displayGroup: {
    flexDirection: "row",
    justifyContent: "space-evenly",
    gap: 10,
    marginBottom: 20,
  },
});

export default HomeScreen;
```

## Step 5: Creating the Pollen Display Component

The `PollenDisplay` component will show the pollen levels for each category. It will use props to display the type, value, and category of pollen.

```javascript
import React from "react";
import { View, Text, StyleSheet } from "react-native";
import { useTheme } from "@rneui/themed";

const PollenDisplay = ({ icon, type, pollenValue, catColor, pollenCat, indicatorPosition }) => {
  const { theme } = useTheme();

  return (
    <View style={[{ backgroundColor: theme.colors.secondary }, styles.pollenDisplay]}>
      <View style={styles.pollenTitle}>
        {icon}
        <Text style={styles.pollenHeading}>{type}</Text>
      </View>
      <View style={styles.pollenValue}>
        <Text style={styles.pollenValueText}>{pollenValue}</Text>
        <Text>pp/m<Text style={styles.superscript}>3</Text></Text>
      </View>
      <Text style={{ color: catColor, ...styles.pollenCat }}>{pollenCat}</Text>
      <View style={styles.colorBox}>
        <View style={[styles.indicator, { left: indicatorPosition }]} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  pollenDisplay: {
    alignItems: "center",
    marginBottom: 20,
    borderRadius: 8,
    padding: 4,
    height: "100%",
    width: "50%",
  },
  pollenHeading: {
    fontSize: 24,
    padding: 10,
  },
  pollenTitle: {
    flexDirection: "row",
    alignItems: "baseline",
  },
  pollenValue: {
    flexDirection: "row",
    alignItems: "baseline",
  },
  pollenValueText: {
    fontSize: 48,
  },
  superscript: {
    fontSize: 12,
    lineHeight: 12,
    textAlignVertical: "top",
  },
  pollenCat: {
    fontSize: 20,
    marginTop: 5,
  },
  colorBox: {
    width: "80%",
    height: 20,
    backgroundColor: "#D8D5C4",
    marginTop: 10,
    position: "relative",
  },
  indicator: {
    position: "absolute",
    width: 10,
    height: 20,
    backgroundColor: "#D62246",
  },
});

export default PollenDisplay;
```

## Step 6: Adding the Location Reset Modal

Next, let's create a modal that allows users to reset their location. This component will take in three props: `visible`, `onClose`, and `onUpdateLocation`.

```javascript
import React, { useState } from "react";
import { View, Text, StyleSheet, Modal, TextInput } from "react-native";
import { useTheme, Button } from "@rneui/themed";

const LocationResetModal = ({ visible, onClose, onUpdateLocation }) => {
  const [newLocation, setNewLocation] = useState("");
  const { theme } = useTheme();

  const handleConfirm = () => {
    onUpdateLocation(newLocation);
    onClose();
  };

  return (
    <Modal
      transparent={true}
      visible={visible}
      animationType="fade"
      onRequestClose={onClose}
    >
      <View style={styles.overlay}>
        <View style={styles.modalContent}>
          <Text style={styles.modalText}>Enter New City</Text>
          <TextInput
            style={styles.input}
            placeholder="Enter New City"
            value={newLocation}
            onChangeText={setNewLocation}
          />
          <View style={styles.modalButtonWrapper}>
            <Button
              title="Confirm"
              size="md"
              color="secondary"
              onPress={handleConfirm}
            />
            <Button
              title="Cancel"
              size="md"
              color="secondary"
              onPress={onClose}
            />
          </View>
        </View>
      </View>
    </Modal>
  );
};

const styles = StyleSheet.create({
  overlay: {
    flex: 1,
    backgroundColor: "rgba(0, 0, 0, 0.5)",
    justifyContent: "flex-start",
    alignItems: "center",
  },
  modalContent: {
    width: "100%",
    height: "100%",
    padding: 20,
    alignItems: "center",
    backgroundColor: "#ccc",
  },
  modalText: {
    fontSize: 18,
    marginBottom: 20,
  },
  modalButtonWrapper: {
    flexDirection: "row",
    gap: 10,
  },
  input: {
    width: "100%",
    padding: 10,
    borderRadius: 15,
    borderWidth: 1,
    marginBottom: 20,
  },
});

export default LocationResetModal;
```

## Conclusion

In this guide, we walked through the process of setting up a React Native application, defining a custom theme, creating key components like `HomeScreen`, `PollenDisplay`, and `LocationResetModal`. By combining various functionalities, you now have a fully functional Pollen Tracker app that can fetch and display pollen data based on the user's location, with the ability to update the location via a modal.

Building an app involves careful planning and integration of different components, but with these steps, you can create a functional and aesthetically pleasing React Native app. 
