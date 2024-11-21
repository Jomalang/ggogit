export default function useGetImageUrl(imageFile = "", imageType = "book") {
  const config = useRuntimeConfig();

  if (imageFile === null || imageFile === "" || imageFile === undefined) {
    if (imageType === "memberBackground") return `background-image.png`;
    return `/png/tree-icon-white.png`; // TODO 디폴트 이미지 처리를 해줘야 함
  }

  if (imageFile.substring(0, 4) === "http") {
    return imageFile;
  } else {
    return `${config.public.apiBase}/images/${imageType}/${imageFile}`;
  }
}
