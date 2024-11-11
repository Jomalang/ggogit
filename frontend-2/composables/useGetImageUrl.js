export default function useGetImageUrl(imageFile = "", imageType = "book") {
  const config = useRuntimeConfig();
  if (imageFile.substring(0, 4) === "http") {
    return imageFile;
  } else {
    return `${config.public.apiBase}/images/${imageType}/${imageFile}`;
  }
}
